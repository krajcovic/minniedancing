package cz.krajcovic.dancingminnie.utils

import cz.krajcovic.dancingminnie.TalsecApplication

class Utils {
    fun computeSigningCertificateHash(any: Any) : String {
        return any.hashCode().toString()
    }
}