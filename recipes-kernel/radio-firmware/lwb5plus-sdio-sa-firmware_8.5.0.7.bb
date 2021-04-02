SUMMARY = "Laird Connectivity firmware for the LWB5+ SDIO/UART module with a single antenna"

SRC_URI += "${LRD_LWB_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "a7f1feae7a1c7ce68e7a84228d429c15"
SRC_URI[sha256sum] = "56d2c9aa001f419518a9cbb937524922e2704afb431f915f9bf46969db5353a1"

include radio-firmware-lwb.inc
