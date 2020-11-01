SUMMARY = "Laird Connectivity Sterling 60 Firmware SDIO-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "fb7dd972728f2773a3c89b98ff4e2702"
SRC_URI[sha256sum] = "ffd38624d34969e5ea72d2ba68e01614a4b709eb62f2fae1f4b9f63a96209849"

include radio-firmware.inc
