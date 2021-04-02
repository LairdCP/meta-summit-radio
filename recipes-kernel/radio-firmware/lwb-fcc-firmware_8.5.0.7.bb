SUMMARY = "Laird Connectivity firmware for the LWB SDIO/UART module for operation in the United States"

SRC_URI += "${LRD_LWB_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "db1eecc20d57579d90c6ef1fb0669d91"
SRC_URI[sha256sum] = "5a607bc3ca8cea619eae03efa2def406b813603751ffaf3c57c43d7874b4b34e"

include radio-firmware-lwb.inc
