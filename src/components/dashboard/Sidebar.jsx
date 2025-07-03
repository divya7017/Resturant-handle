import { ChefHat, LogOut } from "lucide-react"
import { Badge } from "../ui/badge"
import { useSidebar } from "../ui/sidebar"
import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarHeader,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
  SidebarRail,
} from "../ui/sidebar"
import { navigationConfig } from "../../constants/navigation"

export function AppSidebar({ user, activeView, onViewChange, onLogout }) {
  const navigation = navigationConfig[user.role] || []
  const { state } = useSidebar() // get sidebar state

  return (
    <Sidebar variant="inset" collapsible="icon">
      <SidebarHeader>
        <div className="flex items-center gap-2 px-2 py-2">
          <div className="w-8 h-8 bg-primary rounded-lg flex items-center justify-center">
            <ChefHat className="h-4 w-4 text-primary-foreground" />
          </div>
          {/* Hide name when collapsed */}
          {state !== "collapsed" && (
            <div className="flex flex-col">
              <h2 className="text-lg font-semibold">Restaurant</h2>
            </div>
          )}
        </div>
        {/* Hide user info and role when collapsed */}
        {state !== "collapsed" && (
          <div className="mx-2 p-3 bg-muted rounded-lg">
            <p className="text-sm font-medium truncate">{user.name}</p>
            <p className="text-xs text-muted-foreground truncate">{user.email}</p>
            <Badge variant="secondary" className="mt-1 text-xs">
              {user.role.replace("_", " ").toUpperCase()}
            </Badge>
          </div>
        )}
      </SidebarHeader>

      <SidebarContent>
        <SidebarMenu>
          {navigation.map((item) => {
            const Icon = item.icon
            return (
              <SidebarMenuItem key={item.id}>
                <SidebarMenuButton
                  onClick={() => onViewChange(item.id)}
                  isActive={activeView === item.id}
                  tooltip={item.label}
                >
                  <Icon className="h-4 w-4" />
                  <span>{item.label}</span>
                </SidebarMenuButton>
              </SidebarMenuItem>
            )
          })}
        </SidebarMenu>
      </SidebarContent>

      <SidebarFooter>
        <SidebarMenu>
          <SidebarMenuItem>
            <SidebarMenuButton onClick={onLogout} tooltip="Logout">
              <LogOut className="h-4 w-4" />
              <span>Logout</span>
            </SidebarMenuButton>
          </SidebarMenuItem>
        </SidebarMenu>
      </SidebarFooter>

      <SidebarRail />
    </Sidebar>
  )
}
