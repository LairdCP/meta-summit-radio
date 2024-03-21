SUMMARY = "Summit Backports for TI351"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','sona_ti','sona_ti_nbt',d)}"

RCONFLICTS:${PN} = " \
        kernel-module-60-backports \
        kernel-module-lwb-if-backports \
        kernel-module-msd45-backports \
        kernel-module-msd50-backports \
        kernel-module-nx-backports \
        kernel-module-bdsdmac-backports \
        "

require summit-backports.inc radio-stack-ti-version.inc
