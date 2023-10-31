package uz.os3ketchup.myhelper.presentation.viewmodels

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import uz.os3ketchup.myhelper.domain.AuthRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {


    private val currentUser: FirebaseAuth
        get() = repository.currentUser


    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    var mVerificationId = ""


    fun sendCode(phoneNumber: String, activity: Activity) {
        val options = PhoneAuthOptions.newBuilder(currentUser)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    private var callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            signInWithCredit(p0)
        }

        override fun onVerificationFailed(p0: FirebaseException) {}

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            mVerificationId = p0
        }
    }

    fun signInWithCredit(credential: AuthCredential, activity: Activity = Activity()) {
        _state.value = Progress
        currentUser.signInWithCredential(credential)
            .addOnCompleteListener(activity) {
                if (it.isSuccessful) {
                    _state.value = Result
                    Log.d(SUCCESS, "signInWithCredential:${it.isSuccessful}")
                } else {
                    Log.d(SUCCESS, "signInWithCredential:${it.isSuccessful}")
                    _state.value = Error
                    if (it.exception is FirebaseAuthInvalidCredentialsException){
                        Log.d(SUCCESS, "signInWithCredential:ex")
                    }
                }
            }

    }

    fun signOut(){
        currentUser.signOut()
    }

    companion object{
        const val SUCCESS = "success"
    }

}