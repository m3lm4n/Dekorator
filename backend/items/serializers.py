from rest_framework import serializers
import logics, models

def absolute_media_uri(media, request):
    return request.build_absolute_uri(reverse('media', args=[unicode(media)]))


class ItemSerializer(serializers.ModelSerializer):

    class Meta:
        model = logics.Item

class ReservationSerializer(serializers.ModelSerializer):
	item = ItemSerializer()

	class Meta:
		model = logics.Reservation
		#fields = ('id', 'name', 'date')

class RentItemSerializer(serializers.ModelSerializer):
	res = ReservationSerializer()

	class Meta:
		model = models.RentModel

class ReturnItemSerializer(serializers.ModelSerializer):
	class Meta:
		model = models.ReturnModel
