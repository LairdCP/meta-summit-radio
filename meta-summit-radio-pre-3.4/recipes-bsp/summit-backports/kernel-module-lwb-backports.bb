SUMMARY = "Summit Backports for LWB and LWB5"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','lwb','lwb_nbt',d)}"

RCONFLICTS_${PN} = " \
        kernel-module-60-backports \
        kernel-module-msd45-backports \
        kernel-module-msd50-backports \
        kernel-module-nx-backports \
        "

require summit-backports.inc radio-stack-lwb-version.inc
