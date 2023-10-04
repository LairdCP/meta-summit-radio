SUMMARY = "Summit Backports for LWB and LWB5"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','lwb','lwb_nbt',d)}"

RCONFLICTS:${PN} = " \
        kernel-module-60-backports-summit \
        kernel-module-msd45-backports-summit \
        kernel-module-msd50-backports-summit \
        kernel-module-nx-backports-summit \
        "

require summit-backports.inc radio-stack-lwb-version.inc
