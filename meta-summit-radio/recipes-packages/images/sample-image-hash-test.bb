DESCRIPTION = "Hash Test Image"
LICENSE = "MIT"

inherit core-image

export IMAGE_BASENAME = "${PN}"

IMAGE_FEATURES:remove = "\
	tools-profile \
	tools-debug \
	tools-testapps \
	"

IMAGE_INSTALL += "\
	60-radio-firmware-sdio-sdio \
	60-radio-firmware-sdio-uart \
	60-radio-firmware-pcie-uart \
	60-radio-firmware-pcie-usb \
	60-radio-firmware-usb-uart \
	60-radio-firmware-usb-usb \
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
	kernel-module-lwb-if-backports \
	kernel-module-60-backports \
	summit-supplicant-60 \
	summit-supplicant-lwb-if \
	summit-supplicant-libs-60 \
	summit-networkmanager-60 \
	summit-networkmanager-lwb-if \
	"
