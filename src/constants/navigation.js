import {
  Home,
  Users,
  ShoppingCart,
  Settings,
  ChefHat,
  ClipboardList,
  BarChart3,
  Package,
  UserCheck,
  Calendar,
  DollarSign,
  TrendingUp,
} from "lucide-react"

export const navigationConfig = {
  ADMIN: [
    { id: "overview", label: "Overview", icon: Home, url: "/dashboard" },
    { id: "users", label: "User Management", icon: Users, url: "/dashboard/users" },
    { id: "orders", label: "All Orders", icon: ShoppingCart, url: "/dashboard/orders" },
    { id: "analytics", label: "Analytics", icon: BarChart3, url: "/dashboard/analytics" },
    { id: "inventory", label: "Inventory", icon: Package, url: "/dashboard/inventory" },
    { id: "reports", label: "Reports", icon: TrendingUp, url: "/dashboard/reports" },
    { id: "settings", label: "Settings", icon: Settings, url: "/dashboard/settings" },
  ],
  supervisor: [
    { id: "overview", label: "Overview", icon: Home, url: "/dashboard" },
    { id: "staff", label: "Staff Management", icon: UserCheck, url: "/dashboard/staff" },
    { id: "orders", label: "Orders", icon: ShoppingCart, url: "/dashboard/orders" },
    { id: "schedule", label: "Scheduling", icon: Calendar, url: "/dashboard/schedule" },
    { id: "analytics", label: "Analytics", icon: BarChart3, url: "/dashboard/analytics" },
    { id: "inventory", label: "Inventory", icon: Package, url: "/dashboard/inventory" },
  ],
  WAITER: [
    { id: "overview", label: "Overview", icon: Home, url: "/dashboard" },
    { id: "orders", label: "My Orders", icon: ShoppingCart, url: "/dashboard/orders" },
    { id: "tables", label: "Table Management", icon: ClipboardList, url: "/dashboard/tables" },
    { id: "customers", label: "Customers", icon: Users, url: "/dashboard/customers" },
    { id: "earnings", label: "Earnings", icon: DollarSign, url: "/dashboard/earnings" },
  ],
  kitchen_staff: [
    { id: "overview", label: "Overview", icon: Home, url: "/dashboard" },
    { id: "orders", label: "Kitchen Orders", icon: ChefHat, url: "/dashboard/kitchen-orders" },
    { id: "menu", label: "Menu Items", icon: ClipboardList, url: "/dashboard/menu" },
    { id: "inventory", label: "Kitchen Inventory", icon: Package, url: "/dashboard/kitchen-inventory" },
  ],
}
