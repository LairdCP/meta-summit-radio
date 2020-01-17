SUMMARY = "Laird Sterling 60 Firmware PCIE-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "c4558a099f86f6219bd526294302d1bd"
SRC_URI[sha256sum] = "d689d08a1a041010284f2709456b33299ef28aa21af98f94511b6f518f9ba8bd"

include radio-firmware.inc
