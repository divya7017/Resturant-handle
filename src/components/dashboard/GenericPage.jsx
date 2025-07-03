import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "../ui/card"

export default function GenericPage({ title, description }) {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-3xl font-bold tracking-tight">{title}</h2>
        <p className="text-muted-foreground">{description}</p>
      </div>

      <Card>
        <CardHeader>
          <CardTitle>Coming Soon</CardTitle>
          <CardDescription>This page is under development.</CardDescription>
        </CardHeader>
        <CardContent>
          <p className="text-sm text-muted-foreground">
            The {title.toLowerCase()} functionality will be available soon. Stay tuned for updates!
          </p>
        </CardContent>
      </Card>
    </div>
  )
}
