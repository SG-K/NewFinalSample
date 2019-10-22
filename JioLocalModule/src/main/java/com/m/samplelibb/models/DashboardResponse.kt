package com.m.samplelibb.models


import com.squareup.moshi.Json

data class ListCouponTypesItem(@Json(name = "ApplyTo")
                               val applyTo: String? = "",
                               @Json(name = "ID")
                               val id: String? = "")


data class DashboardModel(@Json(name = "ListCouponTypes")
                          val listCouponTypes: List<ListCouponTypesItem>?,
                          @Json(name = "IsServiceLists")
                          val isServiceLists: String? = "",
                          @Json(name = "ShowTaxCharges")
                          val showTaxCharges: String? = "",
                          @Json(name = "CatalogDimensions")
                          val catalogDimensions: String? = "",
                          @Json(name = "Error")
                          val error: String? = null,
                          @Json(name = "OrdersCount")
                          val ordersCount: String? = "",
                          @Json(name = "ListCards")
                          val listCards: List<ListCardsItem>?,
                          @Json(name = "GraphData")
                          val graphData: GraphData,
                          @Json(name = "CurrencySymbol")
                          val currencySymbol: String? = "",
                          @Json(name = "MessagesCount")
                          val messagesCount: String? = "",
                          @Json(name = "AppOrResCount")
                          val appOrResCount: String? = "",
                          @Json(name = "ShowDeliveryCharges")
                          val showDeliveryCharges: String? = "",
                          @Json(name = "ReviewsCount")
                          val reviewsCount: String? = "")


data class ListCardsItem(@Json(name = "RightButtonTitle")
                         val rightButtonTitle: String? = null,
                         @Json(name = "Description")
                         val description: String? = "",
                         @Json(name = "NavigationURL")
                         val navigationURL: String? = "",
                         @Json(name = "AppID")
                         val appID: String? = "",
                         @Json(name = "HeaderTitle")
                         val headerTitle: String? = "",
                         @Json(name = "CardType")
                         val cardType: String? = "",
                         @Json(name = "Title")
                         val title: String? = "",
                         @Json(name = "BannerID")
                         val bannerID: String? = "",
                         @Json(name = "HeaderIcon")
                         val headerIcon: String? = "",
                         @Json(name = "SingleButtonTitle")
                         val singleButtonTitle: String? = "",
                         @Json(name = "LeftButtonTitle")
                         val leftButtonTitle: String? = null)


data class GraphData(@Json(name = "ThisMonthVisits")
                     val thisMonthVisits: String? = null,
                     @Json(name = "TotalVisits")
                     val totalVisits: String? = null)


