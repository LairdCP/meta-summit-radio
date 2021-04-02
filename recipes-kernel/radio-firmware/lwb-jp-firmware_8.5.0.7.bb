SUMMARY = "Laird Connectivity firmware for the LWB SDIO/UART module for operation in Japan"

SRC_URI += "${LRD_LWB_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "91b0bb9072a8fa85fa8d3778cd593e74"
SRC_URI[sha256sum] = "fdbead31c6cdea84b92a7a52fc607761dd5057af97cc1e0457085f106784354b"

include radio-firmware-lwb.inc
