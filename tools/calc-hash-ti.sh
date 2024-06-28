#! /bin/sh

dir=$(dirname "${0}")
ver=${1}

# shellcheck disable=SC2034
file="radio-stack-ti-hashes.inc"

# shellcheck source=/dev/null
. "${dir}/calc-common.sh"

calc_file "summit_supplicant/laird" "summit_supplicant-src-${ver}.tar.gz" "summit-supplicant-src"
calc_file "lrd-network-manager/src" "summit-network-manager-src-${ver}.tar.xz" "summit-network-manager"
calc_file "backports/laird" "summit-backports-${ver}.tar.bz2" "summit-backports"

for i in WW US JP EU CA AU
do
  calc_file "firmware" "summit-ti351-${i}-firmware-${ver}.tar.bz2" "ti351-${i}-firmware"
done
