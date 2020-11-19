DESCRIPTION = "Hash Test Image"
LICENSE = "MIT"

inherit core-image

export IMAGE_BASENAME = "${PN}"

IMAGE_FEATURES_remove = "\
	tools-profile \
	tools-debug \
	tools-testapps \
	"

IMAGE_INSTALL += "\
	sterling-backports-laird \
	sterling60-firmware-sdio-sdio \
	sterling60-firmware-sdio-uart \
	sterling60-firmware-pcie-uart \
	sterling60-firmware-pcie-usb \
	sterling60-firmware-usb-uart \
	sterling60-firmware-usb-usb \
	sterling-supplicant \
	laird-networkmanager \
	lwb-backports-laird \
	sterling-supplicant-lwb \
	summit-backports-laird \
	"
