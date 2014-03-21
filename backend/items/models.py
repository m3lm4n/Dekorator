from django.db import models

class ItemModel(models.Model):
	name 	= models.CharField(max_length=255, null=False, blank=False)
	image	= models.ImageField(upload_to='images', null=True, blank=True)
	away 	= models.BooleanField(default=False, null=False, blank=False)

class AttributeModel(models.Model):
	name 	= models.CharField(max_length=255, null=False, blank=False)

class ValueModel(models.Model):
	attr    = models.ForeignKey(AttributeModel, null=False, blank=False, related_name='value')
	item    = models.ForeignKey(ItemModel, null=False, blank=False, related_name='attribute')
	value   = models.CharField(max_length=255, null=False, blank=False)

	class Meta:
		unique_together = ("attr", "item")

class ReservationModel(models.Model):
    item    = models.ForeignKey(ItemModel, null=False, blank=False)
    date    = models.DateField(null=False, blank=False)
    name    = models.CharField(max_length=255, null=False, blank=False)

class RentModel(models.Model):
	res     = models.ForeignKey(ReservationModel, null=False, blank=False)
	date 	= models.DateField(auto_now_add=True, blank=True)
	paid	= models.BooleanField(null=False, blank=True, default=False)

class ReturnModel(models.Model):
	rent    = models.ForeignKey(RentModel, null=False, blank=False)
	date    = models.DateField(null=False, blank=False)


