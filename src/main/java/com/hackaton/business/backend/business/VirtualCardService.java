package com.hackaton.business.backend.business;

import com.hackaton.business.backend.expose.virtualCardDTO.VirtualCardSavedResquest;
import io.reactivex.Completable;

public interface VirtualCardService {

    Completable saveVirtualCard(VirtualCardSavedResquest virtualCardSavedResquest);

}
