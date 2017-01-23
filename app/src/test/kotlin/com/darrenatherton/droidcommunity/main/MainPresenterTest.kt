package com.darrenatherton.droidcommunity.main

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
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
    fun viewTitleUpdatedWhenFeedTabSelected() {

        // given that the view is attached
        presenter.viewAttached(view)

        // when the feed tab is selected in the view
        presenter.onTabSelected(presenter.FEED_TAB)

        // then the view title is set for the feed
        verify(view).setTitleForFeed()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun viewTitleUpdatedWhenChatTabSelected() {

        // given that the view is attached
        presenter.viewAttached(view)

        // when the chat tab is selected in the view
        presenter.onTabSelected(presenter.CHAT_TAB)

        // then the view title is set for chat
        verify(view).setTitleForChat()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun viewTitleUpdatedWhenEventsTabSelected() {

        // given that the view is attached
        presenter.viewAttached(view)

        // when the feed tab is selected in the view
        presenter.onTabSelected(presenter.EVENTS_TAB)

        // then the view title is set for events
        verify(view).setTitleForEvents()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun viewTitleNotUpdatedWhenViewNotAttached() {

        // given that the view is not ttached

        // when the feed tab is selected in the view
        presenter.onTabSelected(presenter.FEED_TAB)

        // then the view title is set for the feed
        verify(view, never()).setTitleForFeed()

        verifyNoMoreInteractions(view)
    }

    @Test
    fun doNothingIfInvalidTabSelected() {

        // given that the view is attached
        presenter.viewAttached(view)

        // when the presenter tabSelected is called with an unknown tab
        presenter.onTabSelected(999)

        // then the presenter does not updated the view
        verifyNoMoreInteractions(view)
    }

    @After
    fun tearDown() {}
}