package com.darrenatherton.droidcommunity.main

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock private lateinit var view: MainPresenter.View
    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter()
    }

    @Test
    fun viewUpdatedWhenFeedTabSelected() {

        // given that the view is attached
        presenter.viewAttached(view)

        // when the feed tab is selected in the view
        presenter.onTabSelected(presenter.FEED_TAB)

        // then the view title is set for the feed and the subscriptions menu is enabled
        verify(view).setTitleForFeed()
        verify(view).enableSubscriptionsMenu()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun viewUpdatedWhenChatTabSelected() {

        // given that the view is attached
        presenter.viewAttached(view)

        // when the chat tab is selected in the view
        presenter.onTabSelected(presenter.CHAT_TAB)

        // then the view title is set for chat and the subscriptions menu is disabled
        verify(view).setTitleForChat()
        verify(view).disableSubscriptionsMenu()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun viewUpdatedWhenEventsTabSelected() {

        // given that the view is attached
        presenter.viewAttached(view)

        // when the feed tab is selected in the view
        presenter.onTabSelected(presenter.EVENTS_TAB)

        // then the view title is set for events and the subscriptions menu is disabled
        verify(view).setTitleForEvents()
        verify(view).disableSubscriptionsMenu()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun viewNotUpdatedWhenViewNotAttached() {

        // given that the view is not ttached

        // when the feed tab is selected in the view
        presenter.onTabSelected(presenter.FEED_TAB)

        // then the view title is set for the feed and subscription menu is not modified
        verifyNoMoreInteractions(view)
    }

    @Test
    fun doNothingIfInvalidTabSelected() {

        // given that the view is attached
        presenter.viewAttached(view)

        // when the presenter tabSelected is called with an unknown tab
        presenter.onTabSelected(999)

        // then the presenter does not update the view
        verifyNoMoreInteractions(view)
    }

    @After
    fun tearDown() {}
}