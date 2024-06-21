SUMMARY = "BD-SDMAC Firmware SDIO-UART"

require radio-firmware.inc

LICENSE = "QCA"
NO_GENERIC_LICENSE[QCA] = "LICENSE.qca_firmware NOTICE.qca_firmware"
LIC_FILES_CHKSUM = "\
    file://LICENSE.qca_firmware;md5=e8b1e9e8ce377ca5b2c1098e5690f470 \
    file://NOTICE.qca_firmware;md5=bf9ec5483ed37fb00e52e04831084061 \
    "

require radio-stack-bdsdmac-version.inc
