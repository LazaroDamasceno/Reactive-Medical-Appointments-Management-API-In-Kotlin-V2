package com.api.v1.common

import jakarta.validation.constraints.NotBlank

enum class States {
    AL, AK, AZ, AR, CA, CO, CT, DE, DC,
    FL, GA, HI, ID, IL, IN, IA, KS, KY,
    LA, ME, MD, MA, MI, MN, MS, MO, MT,
    NE, NV, NH, NJ, NM, NY, NC, ND, OH,
    OK, OR, PA, RI, SC, SD, TN, TX, UT,
    VT, VA, WA, WV, WI, WY;

    fun from(@NotBlank state: String): States {
        val parsedState = entries
            .firstOrNull { s -> s == States.valueOf(state) }
        if (parsedState == null) {
            throw NonExistentStateException()
        }
        return parsedState
    }
}