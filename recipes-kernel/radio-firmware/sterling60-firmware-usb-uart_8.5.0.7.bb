SUMMARY = "Laird Connectivity Sterling 60 Firmware USB-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "9b58635670f8e15097fec9a93c2a274d"
SRC_URI[sha256sum] = "9fe094781dfc359b4a84b014ec10cad52a0d37fc7e8b22bb89bca279f01e1f8b"

include radio-firmware-60.inc
