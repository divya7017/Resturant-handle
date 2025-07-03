import { useState } from "react"
import { Button } from "../components/ui/button"
import { Input } from "../components/ui/input"
import { Label } from "../components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "../components/ui/select"
import { Card, CardContent, CardHeader, CardTitle } from "../components/ui/card"
import { ArrowLeft, Mail, Shield, User, CheckCircle } from "lucide-react"

export default function ForgotPassword({ onBack }) {
  const [role, setRole] = useState("")
  const [step, setStep] = useState("select-role")
  const [resetData, setResetData] = useState({
    email: "",
    staffId: "",
    newPin: "",
    confirmPin: "",
    newPassword: "",
    confirmPassword: "",
    verificationCode: "",
  })
  const [errors, setErrors] = useState({})

  const handleInputChange = (field, value) => {
    setResetData((prev) => ({ ...prev, [field]: value }))
    if (errors[field]) {
      setErrors((prev) => ({ ...prev, [field]: "" }))
    }
  }

  const handleRoleSelect = (selectedRole) => {
    setRole(selectedRole)
    setStep("enter-details")
    setResetData({
      email: "",
      staffId: "",
      newPin: "",
      confirmPin: "",
      newPassword: "",
      confirmPassword: "",
      verificationCode: "",
    })
  }

  const validateForm = () => {
    const newErrors = {}
    const isAdminOrSupervisor = role === "admin" || role === "supervisor"

    if (step === "enter-details") {
      if (isAdminOrSupervisor) {
        if (!resetData.email) newErrors.email = "Email is required"
        else if (!/\S+@\S+\.\S+/.test(resetData.email)) newErrors.email = "Email is invalid"
      } else {
        if (!resetData.staffId) newErrors.staffId = "Staff ID is required"
      }
    }

    if (step === "verification") {
      if (!resetData.verificationCode) newErrors.verificationCode = "Verification code is required"
      else if (resetData.verificationCode.length !== 6) newErrors.verificationCode = "Verification code must be 6 digits"

      if (isAdminOrSupervisor) {
        if (!resetData.newPin) newErrors.newPin = "New PIN is required"
        else if (resetData.newPin.length !== 6) newErrors.newPin = "PIN must be 6 digits"
        else if (!/^\d+$/.test(resetData.newPin)) newErrors.newPin = "PIN must contain only numbers"

        if (!resetData.confirmPin) newErrors.confirmPin = "Please confirm your PIN"
        else if (resetData.newPin !== resetData.confirmPin) newErrors.confirmPin = "PINs do not match"
      } else {
        if (!resetData.newPassword) newErrors.newPassword = "New password is required"
        else if (resetData.newPassword.length < 6) newErrors.newPassword = "Password must be at least 6 characters"

        if (!resetData.confirmPassword) newErrors.confirmPassword = "Please confirm your password"
        else if (resetData.newPassword !== resetData.confirmPassword) newErrors.confirmPassword = "Passwords do not match"
      }
    }

    setErrors(newErrors)
    return Object.keys(newErrors).length === 0
  }

  const handleSendVerification = (e) => {
    e.preventDefault()
    if (validateForm()) {
      console.log("Sending verification code for:", { role, ...resetData })
      setStep("verification")
    }
  }

  const handleResetPassword = (e) => {
    e.preventDefault()
    if (validateForm()) {
      console.log("Resetting password for:", { role, ...resetData })
      setStep("success")
    }
  }

  const isAdminOrSupervisor = role === "admin" || role === "supervisor"

  return (
    <div className="min-h-screen bg-[#F9FAFB] flex items-center justify-center p-8">
      <div className="w-full max-w-md space-y-6">
        {/* Header */}
        <div className="text-center space-y-2">
          <h2 className="text-2xl font-bold text-[#1F2937]">Restro</h2>
          <p className="text-[#6B7280]">Restaurant</p>
        </div>

        {/* Back Button */}
        <Button
          variant="ghost"
          onClick={onBack}
          className="text-[#FF7043] hover:text-white hover:bg-[#FF7043] p-0"
        >
          <ArrowLeft className="h-4 w-4 mr-2" />
          Back to Login
        </Button>

        {/* Card */}
        <Card className="bg-white border border-gray-200 shadow-md">
          <CardHeader>
            <CardTitle className="text-[#1F2937] text-xl flex items-center">
              {step === "select-role" && <><Shield className="h-5 w-5 mr-2 text-[#FF7043]" />Reset Password</>}
              {step === "enter-details" && <><Mail className="h-5 w-5 mr-2 text-[#FF7043]" />{isAdminOrSupervisor ? "Enter Email" : "Enter Staff ID"}</>}
              {step === "verification" && <><User className="h-5 w-5 mr-2 text-[#FF7043]" />Verify & Reset</>}
              {step === "success" && <><CheckCircle className="h-5 w-5 mr-2 text-[#4CAF50]" />Success</>}
            </CardTitle>
          </CardHeader>
          <CardContent>
            {/* Step 1 */}
            {step === "select-role" && (
              <div className="space-y-4">
                <p className="text-[#6B7280] text-sm">Select your role to proceed with password reset</p>
                <div className="space-y-2">
                  <Label htmlFor="reset-role" className="text-[#455A64]">Select Role</Label>
                  <Select value={role} onValueChange={handleRoleSelect}>
                    <SelectTrigger className="bg-white border border-gray-300 text-[#1F2937]">
                      <SelectValue placeholder="Choose your role" />
                    </SelectTrigger>
                    <SelectContent className="bg-white border border-gray-300">
                      {["admin", "supervisor", "waiter", "kitchen-staff"].map((r) => (
                        <SelectItem key={r} value={r} className="text-[#1F2937] hover:bg-gray-100 capitalize">
                          {r.replace("-", " ")}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>
              </div>
            )}

            {/* Step 2 */}
            {step === "enter-details" && (
              <form onSubmit={handleSendVerification} className="space-y-4">
                <p className="text-[#6B7280] text-sm">
                  {isAdminOrSupervisor ? "Enter your email address to receive a verification code" : "Enter your staff ID to receive a verification code"}
                </p>

                {isAdminOrSupervisor ? (
                  <div className="space-y-2">
                    <Label className="text-[#455A64]">Email Address</Label>
                    <Input
                      type="email"
                      value={resetData.email}
                      onChange={(e) => handleInputChange("email", e.target.value)}
                      className="bg-white border border-gray-300 text-[#1F2937] placeholder-[#6B7280]"
                    />
                    {errors.email && <p className="text-[#EF5350] text-sm">{errors.email}</p>}
                  </div>
                ) : (
                  <div className="space-y-2">
                    <Label className="text-[#455A64]">Staff ID</Label>
                    <Input
                      type="text"
                      value={resetData.staffId}
                      onChange={(e) => handleInputChange("staffId", e.target.value)}
                      className="bg-white border border-gray-300 text-[#1F2937] placeholder-[#6B7280]"
                    />
                    {errors.staffId && <p className="text-[#EF5350] text-sm">{errors.staffId}</p>}
                  </div>
                )}

                <Button type="submit" className="w-full bg-[#FF7043] hover:bg-[#e55e33] text-white">
                  Send Verification Code
                </Button>
              </form>
            )}

            {/* Step 3 */}
            {step === "verification" && (
              <form onSubmit={handleResetPassword} className="space-y-4">
                <div className="space-y-2">
                  <Label className="text-[#455A64]">Verification Code</Label>
                  <Input
                    type="text"
                    value={resetData.verificationCode}
                    onChange={(e) => handleInputChange("verificationCode", e.target.value)}
                    maxLength={6}
                    className="bg-white border border-gray-300 text-[#1F2937] placeholder-[#6B7280]"
                  />
                  {errors.verificationCode && <p className="text-[#EF5350] text-sm">{errors.verificationCode}</p>}
                </div>

                {isAdminOrSupervisor ? (
                  <>
                    <InputField id="new-pin" label="New PIN" value={resetData.newPin} error={errors.newPin} onChange={(e) => handleInputChange("newPin", e.target.value)} />
                    <InputField id="confirm-pin" label="Confirm PIN" value={resetData.confirmPin} error={errors.confirmPin} onChange={(e) => handleInputChange("confirmPin", e.target.value)} />
                  </>
                ) : (
                  <>
                    <InputField id="new-password" label="New Password" value={resetData.newPassword} error={errors.newPassword} onChange={(e) => handleInputChange("newPassword", e.target.value)} />
                    <InputField id="confirm-password" label="Confirm Password" value={resetData.confirmPassword} error={errors.confirmPassword} onChange={(e) => handleInputChange("confirmPassword", e.target.value)} />
                  </>
                )}

                <div className="flex space-x-3">
                  <Button type="button" variant="outline" onClick={() => setStep("enter-details")} className="flex-1 border-gray-300 text-[#1F2937] hover:bg-gray-100">Back</Button>
                  <Button type="submit" className="flex-1 bg-[#FF7043] hover:bg-[#e55e33] text-white">
                    Reset {isAdminOrSupervisor ? "PIN" : "Password"}
                  </Button>
                </div>

                <div className="text-center">
                  <button type="button" onClick={handleSendVerification} className="text-[#FF7043] hover:underline text-sm">
                    Resend verification code
                  </button>
                </div>
              </form>
            )}

            {/* Step 4 */}
            {step === "success" && (
              <div className="space-y-4 text-center">
                <div className="mx-auto w-16 h-16 bg-[#4CAF50] rounded-full flex items-center justify-center">
                  <CheckCircle className="h-8 w-8 text-white" />
                </div>
                <h3 className="text-lg font-semibold text-[#1F2937]">
                  {isAdminOrSupervisor ? "PIN Reset Successfully!" : "Password Reset Successfully!"}
                </h3>
                <p className="text-[#6B7280] text-sm">
                  Your {isAdminOrSupervisor ? "PIN" : "password"} has been updated. You can now log in with your new credentials.
                </p>
                <Button onClick={onBack} className="w-full bg-[#FF7043] hover:bg-[#e55e33] text-white">
                  Back to Login
                </Button>
              </div>
            )}
          </CardContent>
        </Card>
      </div>
    </div>
  )
}

function InputField({ id, label, value, onChange, error }) {
  return (
    <div className="space-y-2">
      <Label htmlFor={id} className="text-[#455A64]">{label}</Label>
      <Input
        id={id}
        type="password"
        value={value}
        onChange={onChange}
        className="bg-white border border-gray-300 text-[#1F2937] placeholder-[#6B7280]"
      />
      {error && <p className="text-[#EF5350] text-sm">{error}</p>}
    </div>
  )
}