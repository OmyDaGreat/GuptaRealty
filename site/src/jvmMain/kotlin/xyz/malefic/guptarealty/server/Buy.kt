package xyz.malefic.guptarealty.server

import org.http4k.core.ContentType.Companion.APPLICATION_JSON
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import xyz.malefic.guptarealty.model.Property
import kotlin.uuid.Uuid

val buyRoutes: Array<RoutingHttpHandler> = arrayOf(
    "/api/buy/properties" bind GET to {
        val messages =
            listOf(
                Property(Uuid.random(), 2450000.0, "1245 Serene Vista Way, Irvine, CA", 4, 3.0, 3100, "https://lh3.googleusercontent.com/aida-public/AB6AXuCNO9a3Wt73r7849fkQ0dVFYkS9HOTFWKJkt-l92NZLzjEi5RXsGoLkz7MnFZBq3SZnDrHuW7uPYC_AEIswWuB6TMxgK9c02FoE0-9P2JAWoJBKTdnYhAIMKCSYmfVlg091XbXsVkyT2vTeQSKc7ugwxUAe3StTJJOboqFHEOVABdta7dvS_JFN9ocw9mg3uuiu5XhsaxUVfI7Wh46b-MBnsxpDuoS4X36Bj1UWj_skCeMrjQvGjucjDyVFV_i4jtjEXkH18ite5NE", listOf("Just Listed"), false),
                Property(Uuid.random(), 1890000.0, "882 Lavender Lane, Newport Beach, CA", 3, 2.5, 2450, "https://lh3.googleusercontent.com/aida-public/AB6AXuCqbK8Z-oOczW3GYNVvF-qb3BFGWt40kjzhI2agdr2CAcc0ICdVKoU-ggrXOKP0KvuNk8RiRPHMS7h-tK-GMRi8nXbbeDQiRLfBDXKInlPZKrK8vfbXINf6ZXxSxN-WO-L5C7rPCCy2wxDEVf3w0037cfZeM24x5fzK5tg3KCi5NVZNkrOpTv7tloklqYdjoBK_IQuc_0vrdfx2flASEzJ-4fugclrw1CuElcLqAofVvqTXDss26-hc47la85zBx45yzinxsePbjUE", listOf("Open House: Sun"), false),
                Property(Uuid.random(), 3125000.0, "402 Pacific View Ct, Laguna Beach, CA", 5, 5.0, 4200, "https://lh3.googleusercontent.com/aida-public/AB6AXuB7AS53h9Y4YjTFOgiVNJm1GTTsuImynexRFV1f43FLPtBtMXqR-s-X51xwI-cZ8hsCdB3YrSBRauMVepzBPNkonzH4NlIMQw4lvbowy6UzCbvtr_5jqB--PIks3EhYpe3xpDn4zrhJ7r7ILzLl7afbBtU-QsMkb6ZUfh_s1KQdyz2xTz8kFRNcD8r-FucWZQT_j6MEx444KF4d8SFIANRF8PMY2OYB857dn6sPUIX53MjrcM7VikHnQ1QXwkPgMz0wgO0957pa10c", listOf("New Construction"), false),
            )
        Response(OK)
            .header("Content-Type", APPLICATION_JSON.value)
            .body(json.encodeToString(messages))
    }
)
