package com.pm.niraj.matchbikar.translator;

import com.pm.niraj.matchbikar.entity.Offer;

public interface Translator<P, Q> {
    P translate(Q from);
}
