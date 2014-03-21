from rest_framework.response import Response
from rest_framework import status
from rest_framework.generics import GenericAPIView

from django.core.exceptions import ObjectDoesNotExist

import logics, serializers, pdb, models

class ItemResource(GenericAPIView):
    serializer_class = serializers.ItemSerializer
    model = logics.Item

    def get(self, request, item_pk=None):
        if item_pk:
            item = logics.Item.objects.get(pk=item_pk)
            serializer = self.get_serializer(instance=item)

            response = serializer.data
            attributes = models.ValueModel.objects.filter(item=item)
            for attr in attributes:
                response[attr.attr.name] = attr.value
        else:
            items = logics.Item.objects.all()
            serializer = self.get_serializer(instance=items, many=True)
            response = serializer.data
        return Response(data=response, status=status.HTTP_200_OK)

    def post(self, request):
        serializer = self.get_serializer(data=request.DATA)
        if serializer.is_valid():
            if 'image' in request.FILES:
                serializer.object.image = request.FILES['image']
            serializer.object.save()
            return Response(data=serializer.data, status=status.HTTP_200_OK)

class ReservationResource(GenericAPIView):
    serializer_class = serializers.ReservationSerializer
    model = logics.Reservation

    def get(self, request, item_pk=None):
        if item_pk:
            reservation = logics.Reservation.objects.filter(pk = item_pk)
            serializer = self.get_serializer(instance=reservation, many=True)
            return Response(data=serializer.data, status=status.HTTP_200_OK)
        return Response(status=status.HTTP_404_NOT_FOUND)

    def post(self, request, item_pk=None):
        if item_pk:
            item = logics.Item.objects.get(pk=item_pk)
            serializer = self.get_serializer(data=request.DATA)
            if serializer.is_valid():
                serializer.object.item = item
                serializer.object.save()
                return Response(data=serializer.data, status=status.HTTP_200_OK)
        return Response(status=status.HTTP_400_BAD_REQUEST)

class ReservationCancelResource(GenericAPIView):
    serializer_class = serializers.ReservationSerializer

    def post(self, request, res_pk=None):
        if res_pk:
            reservation = logics.Reservation.objects.get(pk=res_pk)
            reservation.delete()
            return Response(status=status.HTTP_200_OK)
        else:
            return Response(status=status.HTTP_400_BAD_REQUEST)

class RentItemResource(GenericAPIView):
    serializer_class = serializers.RentItemSerializer

    def post(self, request, res_pk=None):
        if res_pk:
            res = logics.Reservation.objects.get(pk=res_pk)

            res.item.away = True
            res.item.save()

            rentItem = models.RentModel.objects.create(res=res)
            serializer = self.get_serializer(instance=rentItem)
            return Response(data=serializer.data, status=status.HTTP_200_OK)
        return Reservation(status=status.HTTP_404_NOT_FOUND)

class ReturnItemResource(GenericAPIView):
    serializer_class = serializers.RentItemSerializer

    def post(self, request, item_pk):
        if item_pk:
            try:
                item = logics.Item.objects.get(pk=item_pk)

                rent = models.RentModel.objects.get(res__item__id = item_pk)
                rent.delete()
                item.away = False
                item.save()
                return Response(status=status.HTTP_200_OK)
            except ObjectDoesNotExist:
                pass
        return Response(status=status.HTTP_404_NOT_FOUND)


