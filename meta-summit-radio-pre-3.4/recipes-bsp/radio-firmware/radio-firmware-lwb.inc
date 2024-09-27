require radio-firmware.inc
require radio-stack-lwb-version.inc

LICENSE = "Ezurio"
NO_GENERIC_LICENSE[Ezurio] = "LICENSE"
LIC_FILES_CHKSUM = "file://LICENSE;md5=53d3628b28a0bc3caea61587feade5f9"

FILES_${PN} += "${sysconfdir}"

LWB_REGDOMAIN ?= ""

do_install_append() {
    if [ ! -z ${LWB_REGDOMAIN} ]; then
        install -d  ${D}${sysconfdir}/modprobe.d
        echo "options brcmfmac regdomain=\"${LWB_REGDOMAIN}\"" > ${D}${sysconfdir}/modprobe.d/brcmfmac_regd.conf
    fi
}
