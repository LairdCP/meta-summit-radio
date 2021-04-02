SUMMARY = "Laird Connectivity firmware for the LWB5 SDIO/UART module for operation in Japan"

SRC_URI += "${LRD_LWB_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "540f6055548536068d5b86eeb3fd1dd0"
SRC_URI[sha256sum] = "e58e36ce0f10883e24bdb4c0dd86bf9eabff90003b12204d68d29d720933efc9"

include radio-firmware-lwb.inc
