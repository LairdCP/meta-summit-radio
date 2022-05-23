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
	sterling60-firmware-sdio-sdio \
	sterling60-firmware-sdio-uart \
	sterling60-firmware-pcie-uart \
	sterling60-firmware-pcie-usb \
	sterling60-firmware-usb-uart \
	sterling60-firmware-usb-usb \
	lwb-etsi-firmware \
	lwb-fcc-firmware \
	lwb-jp-firmware \
	lwb5-etsi-firmware \
	lwb5-fcc-firmware \
	lwb5-ic-firmware \
	lwb5-jp-firmware \
	lwb5plus-sdio-div-firmware \
	lwb5plus-sdio-sa-firmware \
	lwb5plus-sdio-sa-m2-firmware \
	lwb5plus-usb-div-firmware \
	lwb5plus-usb-sa-firmware \
	lwb5plus-usb-sa-m2-firmware \
	lwbplus-firmware \
	kernel-module-lwb-backports-laird \
	kernel-module-lwb5p-backports-laird \
	kernel-module-sterling-backports-laird \
	sterling-supplicant \
	sterling-supplicant-lwb \
	lrd-networkmanager-sterling \
	"
