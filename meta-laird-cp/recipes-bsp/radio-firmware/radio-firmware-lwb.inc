require radio-firmware.inc radio-stack-lwb-version.inc

LICENSE = "Laird"
NO_GENERIC_LICENSE[Laird] = "LICENSE"
LIC_FILES_CHKSUM = "file://LICENSE;md5=53d3628b28a0bc3caea61587feade5f9"

FILES:${PN} += "${sysconfdir}"

LWB_REGDOMAIN ?= ""

do_install:append() {
    if [ ! -z ${LWB_REGDOMAIN} ]; then
        install -d  ${D}${sysconfdir}/modprobe.d
        echo "options brcmfmac regdomain=\"${LWB_REGDOMAIN}\"" > ${D}${sysconfdir}/modprobe.d/brcmfmac_regd.conf
    fi
}
