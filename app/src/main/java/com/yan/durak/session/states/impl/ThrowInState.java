package com.yan.durak.session.states.impl;

import com.yan.durak.gamelogic.cards.Card;
import com.yan.durak.services.CardsTouchProcessorService;
import com.yan.durak.services.PileLayouterManagerService;
import com.yan.durak.services.PileManagerService;
import com.yan.durak.session.states.BaseDraggableState;

import java.util.ArrayList;

import glengine.yan.glengine.service.ServiceLocator;

/**
 * Created by Yan-Home on 4/26/2015.
 */
public class ThrowInState extends BaseDraggableState {

    private int mMaxCardsToThrowInAmount;
    private ArrayList<Card> mAllowedCardsToThrowIn;
    private ArrayList<Card> mChosenCardsToThrowIn;

    public ThrowInState() {
        mAllowedCardsToThrowIn = new ArrayList<>();
        mChosenCardsToThrowIn = new ArrayList<>();
    }

    @Override
    public ActivePlayerStateDefinition getStateDefinition() {
        return ActivePlayerStateDefinition.REQUEST_THROW_IN;
    }

    @Override
    public void resetState() {
        super.resetState();
        mAllowedCardsToThrowIn.clear();
        mChosenCardsToThrowIn.clear();
        mMaxCardsToThrowInAmount = 0;
    }

    public int getMaxCardsToThrowInAmount() {
        return mMaxCardsToThrowInAmount;
    }

    public ArrayList<Card> getAllowedCardsToThrowIn() {
        return mAllowedCardsToThrowIn;
    }

    public ArrayList<Card> getChosenCardsToThrowIn() {
        return mChosenCardsToThrowIn;
    }

    public void setMaxCardsToThrowInAmount(final int maxCardsToThrowInAmount) {
        mMaxCardsToThrowInAmount = maxCardsToThrowInAmount;
    }

    @Override
    public void applyState() {
        ServiceLocator.locateService(CardsTouchProcessorService.class).register();
        ServiceLocator.locateService(PileLayouterManagerService.class).getPileLayouterForPile(ServiceLocator.locateService(PileManagerService.class).getBottomPlayerPile()).layout();

    }
}