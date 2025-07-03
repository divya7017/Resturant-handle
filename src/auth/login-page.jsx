import React, { useState } from "react"
import { useNavigate } from "react-router-dom"
import { jwtDecode } from "jwt-decode"

// UI Components
import { Button } from "../components/ui/button"
import { Input } from "../components/ui/input"
import { Label } from "../components/ui/label"
import {
  Card,
  CardContent,
  CardHeader,
  CardTitle,
} from "../components/ui/card"

// Icons
import { ChefHat, Users, Clock, TrendingUp, Eye, EyeOff } from "lucide-react"

// Forgot Password Component
import ForgotPassword from "./forget-password"

export default function LoginPage() {
  const navigate = useNavigate()

  const [showForgotPassword, setShowForgotPassword] = useState(false)
  const [credentials, setCredentials] = useState({ id: "", password: "" })
  const [isLoading, setIsLoading] = useState(false)
  const [showPassword, setShowPassword] = useState(false)

  const handleInputChange = (field, value) => {
    setCredentials((prev) => ({ ...prev, [field]: value }))
  }

  const handleLogin = async (e) => {
    e.preventDefault()
    setIsLoading(true)

    try {
      const response = await fetch("https://aaa7-2405-201-6807-501b-5188-c737-c42b-7fad.ngrok-free.app/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          username: credentials.id,
          password: credentials.password,
        }),
      })

      if (!response.ok) {
        const errorText = await response.text()
        throw new Error(errorText || "Login failed")
      }

      const userData = await response.json()

      localStorage.setItem("user", JSON.stringify(userData))
      if (userData.token) {
        localStorage.setItem("token", userData.token)
        const decoded = jwtDecode(userData.token)
      }

      navigate("/dashboard")
    } catch (err) {
      console.error("Login failed:", err.message)
      alert("Login failed. Please check your credentials.")
    } finally {
      setIsLoading(false)
    }
  }

  if (showForgotPassword) {
    return <ForgotPassword onBack={() => setShowForgotPassword(false)} />
  }

  return (
    <div className="min-h-screen bg-[#F9FAFB] flex">
      {/* Left Panel */}
      <div className="hidden lg:flex lg:w-1/2 bg-[#ECEFF1] p-8 flex-col justify-center">
        <div className="max-w-md mx-auto space-y-6">
          <img
            src="https://images.pexels.com/photos/958545/pexels-photo-958545.jpeg?auto=compress&cs=tinysrgb&w=600"
            alt="Restaurant"
            className="w-full h-64 object-cover rounded-lg shadow"
          />
          <div className="text-[#1F2937] space-y-4">
            <h1 className="text-3xl font-bold">
              RestroManager: <br />
              <span className="text-[#FF7043]">Streamline Your Restaurant</span>
            </h1>
            <p className="text-[#6B7280] text-lg">
              Manage orders, staff, and menus effortlessly
            </p>
          </div>
          <div className="grid grid-cols-2 gap-4 text-[#455A64]">
            {[
              ["Kitchen Management", <ChefHat key="chef" className="h-5 w-5" />],
              ["Staff Control", <Users key="users" className="h-5 w-5" />],
              ["Order Tracking", <Clock key="clock" className="h-5 w-5" />],
              ["Analytics", <TrendingUp key="trending" className="h-5 w-5" />],
            ].map(([label, Icon], idx) => (
              <div key={idx} className="flex items-center space-x-2">
                {Icon}
                <span className="text-sm">{label}</span>
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Right Panel */}
      <div className="w-full lg:w-1/2 flex items-center justify-center p-8 relative">
        <div className="w-full max-w-md space-y-6">
          <div className="text-center space-y-2">
            <h2 className="text-2xl font-bold text-[#1F2937]">Restro</h2>
            <p className="text-[#6B7280]">Restaurant Management Portal</p>
          </div>

          <Card className="bg-white border border-[#E5E7EB] text-[#1F2937] shadow-md">
            <CardHeader>
              <CardTitle className="text-xl">Log in</CardTitle>
            </CardHeader>

            <CardContent>
              <form onSubmit={handleLogin} className="space-y-4">
                {/* ID Field */}
                <div className="space-y-2">
                  <Label htmlFor="id">ID</Label>
                  <Input
                    id="id"
                    type="text"
                    placeholder="Enter your ID"
                    value={credentials.id}
                    autoComplete="username"
                    onChange={(e) => handleInputChange("id", e.target.value)}
                    required
                  />
                </div>

                {/* Password Field with Toggle */}
                <div className="space-y-2 relative">
                  <Label htmlFor="password">Password</Label>
                  <Input
                    id="password"
                    type={showPassword ? "text" : "password"}
                    placeholder="Enter your password"
                    value={credentials.password}
                    autoComplete="current-password"
                    onChange={(e) => handleInputChange("password", e.target.value)}
                    required
                    className="pr-10"
                  />
                  <button
                    type="button"
                    className="absolute top-[36px] right-3 text-gray-500 hover:text-gray-700"
                    onClick={() => setShowPassword((prev) => !prev)}
                    tabIndex={-1}
                  >
                    {showPassword ? <EyeOff className="w-5 h-5" /> : <Eye className="w-5 h-5" />}
                  </button>
                </div>

                {/* Login Button */}
                <Button
                  type="submit"
                  className="w-full bg-[#FF7043] hover:bg-[#e25c35] text-white"
                  disabled={isLoading}
                >
                  {isLoading ? "Logging in..." : "Log in"}
                </Button>

                {/* Forgot Password Link */}
                <div className="text-center">
                  <button
                    type="button"
                    className="text-[#FF7043] hover:text-[#e25c35] text-sm underline"
                    onClick={() => setShowForgotPassword(true)}
                  >
                    Forgot your password?
                  </button>
                </div>
              </form>
            </CardContent>
          </Card>
        </div>
      </div>
    </div>
  )
}
