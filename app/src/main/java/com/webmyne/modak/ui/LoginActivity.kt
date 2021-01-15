package com.webmyne.modak.ui

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.webmyne.modak.R
import com.webmyne.modak.custome.MDToast
import com.webmyne.modak.helper.DatabaseHelper
import com.webmyne.modak.helper.Functions
import com.webmyne.modak.helper.PrefUtils
import com.webmyne.modak.model.User
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import java.util.*


class LoginActivity : BaseActivity() {
    private val RC_SIGN_IN = 7
    var fb_id: String = ""
    var fb_email: String = ""
    var fb_name: String = ""
    var bundle = Bundle()
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var callbackManager: CallbackManager? = null
    val db: DatabaseHelper = DatabaseHelper(this)

    companion object {
        fun launchActivity(activity: BaseActivity?) {
            if (activity != null) {
                val intent = Intent(activity, LoginActivity::class.java)
                Functions.fireIntent(activity, intent, true, false)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initview()
        actioListner()

    }

    private fun initview() {
        callbackManager = CallbackManager.Factory.create()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun actioListner() {
        btnLogin.setOnClickListener {
            if (edtEmail_login.text.toString().trim().length == 0) {
                Functions.showToast(this, getString(R.string.please_enter_email), MDToast.TYPE_INFO)
                return@setOnClickListener
            }
            if (!Functions.emailValidation(edtEmail_login.text.toString().trim())) {
                Functions.showToast(
                    this,
                    getString(R.string.please_enter_registred_email),
                    MDToast.TYPE_INFO
                )
                return@setOnClickListener
            }
            if (edtPassword_Login.text.toString().trim().length == 0) {
                Functions.showToast(
                    this,
                    getString(R.string.please_enter_password),
                    MDToast.TYPE_INFO
                )
                return@setOnClickListener
            }
            if (!db.isUserAlreadyExist(
                    edtEmail_login.text.toString().trim(),
                    edtPassword_Login.text.toString().trim()
                )
            ) {
                Functions.showToast(
                    this,
                    getString(R.string.email_or_password_not_valid),
                    MDToast.TYPE_INFO
                )
                return@setOnClickListener
            } else {
                var userdetails: MutableList<User> = mutableListOf()
                userdetails.addAll(
                    db.getAllDetailsUserByEmailId(
                        edtEmail_login.text.toString().trim(),
                        PrefUtils.getUserID(this)
                    )
                )
                PrefUtils.setUserEmail(this, edtEmail_login.text.toString().trim())
                PrefUtils.setUserLoggedIn(this, true)
                Functions.showToast(
                    this,
                    "Welcome " + userdetails.get(0).username + " !! ",
                    MDToast.TYPE_SUCCESS
                )
                DashboardActivity.launchActivity(this)
            }

        }
        layoutSignUp.setOnClickListener {
            SignupActivity.launchActivity(this)
        }
        imgFbSignIn.setOnClickListener {
            FBSignIn()
        }
        imgGoogleSignIn.setOnClickListener {
            GoolgleSignIn()

        }

        txtForgetPassword.setOnClickListener {
            ForgetPasswordActivity.launchActivity(this)
        }
        txtConnecting.setOnClickListener {
            DashboardActivity.launchActivity(this)
        }

        txtPasswordVisibility.setOnClickListener {
            if (txtPasswordVisibility.text.toString().equals("SHOW")) {
                edtPassword_Login.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                txtPasswordVisibility.text = "HIDE"
            } else {
                edtPassword_Login.transformationMethod = PasswordTransformationMethod.getInstance()
                txtPasswordVisibility.text = "SHOW"
            }
        }

    }

    private fun GoolgleSignIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    private fun FBSignIn() {
        if (Functions.isConnected(this)) {
            FacebookSdk.sdkInitialize(applicationContext)
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email"))
            callbackManager = CallbackManager.Factory.create()

            LoginManager.getInstance()
                .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {

                        val request = GraphRequest.newMeRequest(
                            loginResult.accessToken
                        ) { `object`, response ->
                            try {
                                disconnectFromFacebook()
                                fb_email = response.jsonObject.getString("email")
                                fb_id = response.jsonObject.getString("id")
                                fb_name = response.jsonObject.getString("first_name")
                                Log.e("Data", "email" + fb_email)
                                Log.e("Data", "fb_id" + fb_id)
                                Log.e("Data", "fb_name" + fb_name)
                                DashboardActivity.launchActivity(this@LoginActivity)
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Welcome" + fb_name + "!!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // bundle.putString((FirebaseAnalytics.Param.SIGN_UP_METHOD)


                            } catch (e: JSONException) {
                                e.printStackTrace()
                                if (e.message.toString() == "No value for email") {
                                    Log.e("message", "No value for email")
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Proper Email Required",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    disconnectFromFacebook()
                                }
                            }
                        }
                        val parameters = Bundle()
                        parameters.putString("fields", "id,email,first_name,last_name")
                        request.parameters = parameters
                        request.executeAsync()
                    }

                    override fun onCancel() {
                        Log.e("Cancel", "fb")
                    }

                    override fun onError(exception: FacebookException) {
                        AccessToken.setCurrentAccessToken(null)
                        LoginManager.getInstance()
                            .logInWithReadPermissions(this@LoginActivity, Arrays.asList("email"))
                    }

                })
        } else {
            Functions.showToast(this, getString(R.string.internet_connection), MDToast.TYPE_ERROR)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
            super.onActivityResult(requestCode, resultCode, data)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
            callbackManager!!.onActivityResult(requestCode, resultCode, data)
        }

    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>?) {
        signout()
        try {
            val account = task!!.getResult(ApiException::class.java)
            var google_email = account!!.email.toString()
            var google_id = account.id.toString()
            var google_name = account.displayName.toString()
            Log.e("GLogin", "Email" + google_email)
            Log.e("GLogin", "google_id" + google_id)
            Log.e("GLogin", "google_name" + google_name)
            var inttent = Intent(this, DashboardActivity::class.java)
            Toast.makeText(this, "Welcome" + google_name + "!!!", Toast.LENGTH_SHORT).show()
            startActivity(inttent)

        } catch (e: ApiException) {
            Log.e("GLogin", e.message)
        }

    }

    private fun signout() {

    }

    private fun disconnectFromFacebook() {
        GraphRequest(
            AccessToken.getCurrentAccessToken(),
            "/me/permissions/",
            null,
            HttpMethod.DELETE,
            GraphRequest.Callback { LoginManager.getInstance().logOut() }).executeAsync()
    }


}

