SUMMARY = "Laird Connectivity firmware for the LWB5 SDIO/UART module for operation in Canada"

SRC_URI += "${LRD_LWB_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "c565d682a48297e1fa817680171f62d6"
SRC_URI[sha256sum] = "b265599ef70ba5ac6772a8eec2d93b7447e0e125f17b41a62e841fd0a7218fac"

include radio-firmware-lwb.inc
