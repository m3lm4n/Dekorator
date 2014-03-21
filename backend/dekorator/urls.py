from django.conf.urls.static import static
from django.conf.urls import patterns, include, url
from django.conf import settings

import items.resources as resources


# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('',

    url(r'^items/$', resources.ItemResource.as_view(), name='all_items'),
    url(r'^items/(?P<item_pk>[0-9]+)/$', resources.ItemResource.as_view(), name='item'),
    url(r'^items/(?P<item_pk>[0-9]+)/return/$', resources.ReturnItemResource.as_view(), name='reutrn_item'),
    url(r'^items/(?P<item_pk>[0-9]+)/reservations/$', resources.ReservationResource.as_view(), name='item_reservations'),
    url(r'^reservations/(?P<item_pk>[0-9]+)/$', resources.ReservationResource.as_view(), name='reservation'),
    url(r'^reservations/(?P<res_pk>[0-9]+)/cancel/$', resources.ReservationCancelResource.as_view(), name='cancel_reservation'),
    url(r'^reservations/(?P<res_pk>[0-9]+)/rent/$', resources.RentItemResource.as_view(), name='rent_item'),
    url(r'^media/(?P<path>.*)$', 'django.views.static.serve', 
        {'document_root': settings.MEDIA_ROOT}, name="media"),

    # Examples:
    # url(r'^$', 'dekorator.views.home', name='home'),
    # url(r'^dekorator/', include('dekorator.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    # url(r'^admin/', include(admin.site.urls)),
)

