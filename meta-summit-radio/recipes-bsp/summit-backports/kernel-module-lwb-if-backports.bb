SUMMARY = "Summit Backports for LWBxx and IFxxx"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','lwb','lwb_nbt',d)}"

RCONFLICTS:${PN} = " \
        kernel-module-60-backports \
        kernel-module-ti-backports \
        kernel-module-msd45-backports \
        kernel-module-msd50-backports \
        kernel-module-nx-backports \
        kernel-module-bdsdmac-backports \
        "

require summit-backports.inc radio-stack-lwb-version.inc
