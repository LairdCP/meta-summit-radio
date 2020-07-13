SUMMARY = "Laird Connectivity Sterling 60 Firmware SDIO-UART"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "557a575620f2f4b45c99c62d8afecf3b"
SRC_URI[sha256sum] = "46dc1f9d219d4298f4f67f81cb996fb814932d23ec80ca617870b25f3a600df4"

include radio-firmware.inc
