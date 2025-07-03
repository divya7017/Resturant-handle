import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "../ui/card"

export default function OrdersPage({ user }) {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-3xl font-bold tracking-tight">Orders</h2>
        <p className="text-muted-foreground">Manage and track all orders for your restaurant.</p>
      </div>

      <Card>
        <CardHeader>
          <CardTitle>Recent Orders</CardTitle>
          <CardDescription>Latest orders from your restaurant</CardDescription>
        </CardHeader>
        <CardContent>
          <div className="space-y-4">
            <div className="flex items-center justify-between p-4 border rounded-lg">
              <div>
                <p className="font-medium">Order #1234</p>
                <p className="text-sm text-muted-foreground">Table 5 • 2 items</p>
              </div>
              <div className="text-right">
                <p className="font-medium">$24.99</p>
                <p className="text-sm text-green-600">Completed</p>
              </div>
            </div>

            <div className="flex items-center justify-between p-4 border rounded-lg">
              <div>
                <p className="font-medium">Order #1235</p>
                <p className="text-sm text-muted-foreground">Table 3 • 4 items</p>
              </div>
              <div className="text-right">
                <p className="font-medium">$45.50</p>
                <p className="text-sm text-yellow-600">In Progress</p>
              </div>
            </div>

            <div className="flex items-center justify-between p-4 border rounded-lg">
              <div>
                <p className="font-medium">Order #1236</p>
                <p className="text-sm text-muted-foreground">Table 8 • 3 items</p>
              </div>
              <div className="text-right">
                <p className="font-medium">$32.75</p>
                <p className="text-sm text-blue-600">Pending</p>
              </div>
            </div>
          </div>
        </CardContent>
      </Card>
    </div>
  )
}
