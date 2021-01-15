package com.webmyne.modak.ui


import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.annotation.NonNull
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.webmyne.modak.R
import com.webmyne.modak.custome.MDToast
import com.webmyne.modak.helper.Functions
import kotlinx.android.synthetic.main.activity_otp_verify.*
import java.util.concurrent.TimeUnit


class OTPActivity : BaseActivity() {
    var randomNumber: Int = 0
    var flag = 0
    var Mobno: String = ""
    var bundle = Bundle()
    var mVerificationId: String = ""
    var mAuth: FirebaseAuth? = null

    companion object {
        fun launchActivity(activity: BaseActivity?, mobno: String) {
            if (activity != null) {
                var intent = Intent(activity, OTPActivity::class.java)
                intent.putExtra("MobNo", mobno)
                Functions.fireIntent(activity, intent, true)
            }
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)
        mAuth = FirebaseAuth.getInstance();
        initview()
        actionListner()


    }
    private fun initview() {
        Mobno = intent.getStringExtra("MobNo")
        edtMobileNumber.setText(Mobno)
        Log.e("MobNo", "Mobno" + Mobno)
        edtOpt1.addTextChangedListener(CodeTextWatcher(null, edtOpt1, edtOpt2))
        edtOpt2.addTextChangedListener(CodeTextWatcher(edtOpt1, edtOpt2, edtOpt3))
        edtOpt3.addTextChangedListener(CodeTextWatcher(edtOpt2, edtOpt3, edtOpt4))
        edtOpt4.addTextChangedListener(CodeTextWatcher(edtOpt3, edtOpt4, edtOpt5))
        edtOpt5.addTextChangedListener(CodeTextWatcher(edtOpt4, edtOpt5, edtOpt6))
        edtOpt6.addTextChangedListener(CodeTextWatcher(edtOpt5, edtOpt6, null))
    }


    private fun actionListner() {
        val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        btnSendOtp.setOnClickListener {
            if (flag == 0) {
                sendOTP()
            } else if (flag == 1) {
                verifyOtp()
            }

        }
        txtResend.setOnClickListener {
            sendOTP()
        }

    }

    private fun verifyOtp() {
        val mEditOtpValue =
            edtOpt1.text.toString() + edtOpt2.text.toString() + edtOpt3.text.toString() + edtOpt4.text.toString() + edtOpt5.text.toString() + edtOpt6.text.toString()
        verifyVerificationCode(mEditOtpValue.toString().trim())
    }

    private fun sendOTP() {

        /*try {
            // Construct data
            val apiKey = "apikey=" + "+kIh3budhn0-NdMJtCHghrbYuZJ5onLtuqG1rmxjbL"
            val random: Random = Random()
            randomNumber = random.nextInt(999999)

            val message = "&message=" + "Hey,Your Mobile verification OTP Code :" + randomNumber
            val sender = "&sender=" + "TXTLCL"
            val numbers = "&numbers=" + edtMobileNumber.text.toString()

            // Send data
            val conn = URL("https://api.textlocal.in/send/?").openConnection() as HttpURLConnection
            val data = apiKey + numbers + message + sender
            conn.doOutput = true
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Length", Integer.toString(data.length))
            conn.outputStream.write(data.toByteArray(charset("UTF-8")))
            val rd = BufferedReader(InputStreamReader(conn.inputStream))
            val stringBuffer = StringBuffer()
            try {
                var line = rd.readLine()
                while (line != null) {
                    stringBuffer.append(line)
                    line = rd.readLine()
                }
            } finally {
                rd.close()
            }
            btnSendOtp.text = "Verify-OTP"
            llOtp.visibility = View.VISIBLE
            llResend.visibility = View.VISIBLE
            edtMobileNumber.isEnabled = false
            edtMobileNumber.isFocusable = false
            edtMobileNumber.isClickable = false
            flag = 1
            Functions.showToast(this, "OTP Send SuccessFully", MDToast.TYPE_SUCCESS)

            // return stringBuffer.toString()
        } catch (e: Exception) {
            Functions.showToast(this, "something went wromg", MDToast.TYPE_ERROR)
            //  println("Error SMS $e")
            //  return "Error $e"
        }
*/

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91" + Mobno.toString().trim(),
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            mCallbacks
        );
    }


    private val mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            //Getting the code sent by SMS
            val code = phoneAuthCredential.smsCode

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                btnSendOtp.text = "Verify-OTP"
                llOtp.visibility = View.VISIBLE
                llResend.visibility = View.VISIBLE
                edtMobileNumber.isEnabled = false
                edtMobileNumber.isFocusable = false
                edtMobileNumber.isClickable = false
                flag = 1
                Functions.showToast(this@OTPActivity, "OTP Send SuccessFully", MDToast.TYPE_SUCCESS)
                //verifying the code

            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Functions.showToast(this@OTPActivity, e.message!!, MDToast.TYPE_ERROR)
        }

        override fun onCodeSent(
            s: String?,
            forceResendingToken: PhoneAuthProvider.ForceResendingToken?
        ) {
            super.onCodeSent(s, forceResendingToken)
            mVerificationId = s!!
            //   mResendToken = forceResendingToken
        }
    }

    private fun verifyVerificationCode(code: String) {
        //creating the credential
        val credential = PhoneAuthProvider.getCredential(mVerificationId, code)
        //signing the user
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential?) {

        mAuth!!.signInWithCredential(credential!!)
            .addOnCompleteListener(
                this,
                object : OnCompleteListener<AuthResult> {
                    override fun onComplete(@NonNull task: Task<AuthResult>) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity
                            Functions.showToast(this@OTPActivity, "You Login  SuccessFully", MDToast.TYPE_SUCCESS)
                            LoginActivity.launchActivity(this@OTPActivity)
                        } else {

                            var message = "Somthing is wrong, we will fix it soon..."
                            Functions.showToast(this@OTPActivity, message, MDToast.TYPE_SUCCESS)
                            if (task.getException() is FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered..."
                                Functions.showToast(this@OTPActivity, message, MDToast.TYPE_SUCCESS)

                            }

                        }
                    }
                })
    }



    class CodeTextWatcher(etPrevious: EditText?, edtOpt1: EditText, edtOpt2: EditText?) :
        TextWatcher {
        var etPrevious = etPrevious
        var etCurrent = edtOpt1
        var etNext = edtOpt2
        override fun afterTextChanged(s: Editable?) {
            if (etNext != null) {
                if (etCurrent != null && etCurrent.text.toString().length == 1) {
                    etNext!!.requestFocus()

                }
            } else {
                if (etCurrent != null && etCurrent.text.toString().length == 1) {
                    // val imm =  getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    // imm.hideSoftInputFromWindow(etCurrent.getWindowToken(), 0)
                }
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (before == 1 && count == 0 && etPrevious != null) {
                etPrevious!!.requestFocus()
            }
        }

    }
}
