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
	kernel-module-sterling-backports-laird \
	sterling60-firmware-sdio-sdio \
	sterling60-firmware-sdio-uart \
	sterling60-firmware-pcie-uart \
	sterling60-firmware-pcie-usb \
	sterling60-firmware-usb-uart \
	sterling60-firmware-usb-usb \
	sterling-supplicant \
	laird-networkmanager \
	kernel-module-lwb-backports-laird \
	kernel-module-lwb5p-backports-laird \
	sterling-supplicant-lwb \
	kernel-module-summit-backports-laird \
	"
