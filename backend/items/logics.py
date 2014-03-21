import models

class Item(models.ItemModel):

    class Meta:
        proxy = True

    def make_reservetion(self, date, name):
        reservation = Resernation(item=self, date=date, name=name)
        reservation.save()

class Reservation(models.ReservationModel):

    class Meta:
        proxy = True