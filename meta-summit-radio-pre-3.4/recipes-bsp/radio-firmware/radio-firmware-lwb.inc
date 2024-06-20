require radio-firmware.inc
require radio-stack-lwb-version.inc

LICENSE = "Ezurio"
NO_GENERIC_LICENSE[Laird] = "LICENSE"
LIC_FILES_CHKSUM = "file://LICENSE.ezurio;md5=fd3dd0630b215465b6f50540642d5b93 \
                    file://LICENSE.cypress;md5=cbc5f665d04f741f1e006d2096236ba7"

FILES_${PN} += "${sysconfdir}"

LWB_REGDOMAIN ?= ""

do_install_append() {
    if [ ! -z ${LWB_REGDOMAIN} ]; then
        install -d  ${D}${sysconfdir}/modprobe.d
        echo "options brcmfmac regdomain=\"${LWB_REGDOMAIN}\"" > ${D}${sysconfdir}/modprobe.d/brcmfmac_regd.conf
    fi
}
