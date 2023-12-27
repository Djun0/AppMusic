package com.example.appmusic.data.rules

object Validator {
    fun validatorUserName(username:String):ValidationResult{
        return ValidationResult(!username.isNullOrEmpty()&& username.length>=2)
    }
    fun validatorEmail(email:String):ValidationResult{
        return ValidationResult(!email.isNullOrEmpty())}
    fun validatorPassword(password:String):ValidationResult{
        return ValidationResult(!password.isNullOrEmpty())
    }
    fun validatorRepeatPassword(repeatPassword:String):ValidationResult{
        return ValidationResult(!repeatPassword.isNullOrEmpty()&& repeatPassword.length>=6)
    }
    fun validatorPrivacyPolicyAcceptance(statusValue: Boolean):ValidationResult{
        return  ValidationResult(statusValue)
    }
}
data class ValidationResult(val status:Boolean= false)