#! /bin/sh

dir=$(dirname "${0}")
ver=${1}

# shellcheck disable=SC2034
file="radio-stack-msd-hashes.inc"

# shellcheck source=/dev/null
. "${dir}/calc-common.sh"

for i in arm-eabi arm-eabihf
do
  calc_file "summit_supplicant/laird" "summit_supplicant_libs_legacy-${i}-${ver}.tar.bz2" "summit-supplicant-libs-legacy-${i}"
done

calc_file "summit_supplicant/laird" "summit_supplicant-src-${ver}.tar.gz" "summit-supplicant-src"
calc_file "backports/laird" "summit-backports-${ver}.tar.bz2" "summit-backports"
calc_file "firmware" "summit-ath6k-6003-firmware-${ver}.tar.bz2" "ath6k-6003-firmware"
calc_file "firmware" "summit-ath6k-6004-firmware-${ver}.tar.bz2" "ath6k-6004-firmware"
