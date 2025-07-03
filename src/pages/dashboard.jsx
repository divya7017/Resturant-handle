"use client"

import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { jwtDecode } from "jwt-decode"
import { SidebarInset, SidebarProvider, SidebarTrigger } from "../components/ui/sidebar"
import { Separator } from "../components/ui/separator"
import {
  Breadcrumb,
  BreadcrumbItem,
  BreadcrumbLink,
  BreadcrumbList,
  BreadcrumbPage,
  BreadcrumbSeparator,
} from "../components/ui/breadcrumb"
import { AppSidebar } from "../components/dashboard/Sidebar"
import OverviewPage from "../components/dashboard/OverviewPage"
import { navigationConfig } from "../constants/navigation"
import GenericPage from "../components/dashboard/GenericPage"
import OrdersPage from "../components/dashboard/orders"

export default function Dashboard() {
  const navigate = useNavigate()
  const [activeView, setActiveView] = useState("overview")
  const [user, setUser] = useState(null)

  useEffect(() => {
    const token = localStorage.getItem("token")
    if (token) {
      try {
        const decoded = jwtDecode(token)
        setUser(decoded)
      } catch (e) {
        navigate("/login")
      }
    } else {
      navigate("/login")
    }
  }, [navigate])

  const handleViewChange = (view) => setActiveView(view)

  const handleLogout = () => {
    localStorage.removeItem("token")
    localStorage.removeItem("user")
    console.log("Logged out")
    navigate("/login")
  }

  if (!user) {
    return (
      <div className="min-h-screen bg-background flex items-center justify-center">
        <div className="text-center">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-primary mx-auto"></div>
          <p className="mt-4 text-muted-foreground">Loading dashboard...</p>
        </div>
      </div>
    )
  }

  const navigation = navigationConfig[user.role] || []
  const currentPage = navigation.find((item) => item.id === activeView)

  const renderContent = () => {
    switch (activeView) {
      case "overview":
        return <OverviewPage user={user} />
      case "orders":
        return <OrdersPage user={user} />
      default:
        return (
          <GenericPage
            title={currentPage?.label || "Page"}
            description={`Manage your ${currentPage?.label.toLowerCase()} here.`}
          />
        )
    }
  }

  return (
    <SidebarProvider>
      <AppSidebar user={user} activeView={activeView} onViewChange={handleViewChange} onLogout={handleLogout} />
      <SidebarInset>
        <header className="flex h-16 shrink-0 items-center gap-2 transition-[width,height] ease-linear group-has-[[data-collapsible=icon]]/sidebar-wrapper:h-12">
          <div className="flex items-center gap-2 px-4">
            <SidebarTrigger className="-ml-1" />
            <Separator orientation="vertical" className="mr-2 h-4" />
            <Breadcrumb>
              <BreadcrumbList>
                <BreadcrumbItem className="hidden md:block">
                  <BreadcrumbLink href="#">Dashboard</BreadcrumbLink>
                </BreadcrumbItem>
                <BreadcrumbSeparator className="hidden md:block" />
                <BreadcrumbItem>
                  <BreadcrumbPage>{currentPage?.label || "Page"}</BreadcrumbPage>
                </BreadcrumbItem>
              </BreadcrumbList>
            </Breadcrumb>
          </div>
        </header>
        <div className="flex flex-1 flex-col gap-4 p-4 pt-0">{renderContent()}</div>
      </SidebarInset>
    </SidebarProvider>
  )
}
