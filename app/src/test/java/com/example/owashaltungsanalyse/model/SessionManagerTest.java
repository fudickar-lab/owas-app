package com.example.owashaltungsanalyse.model;

import com.example.owashaltungsanalyse.model.observation.ISession;
import com.example.owashaltungsanalyse.model.observation.ISessionInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;



public class SessionManagerTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Mock
    private ISession currentSession;
    @Mock
    private ISessionInfo sessionInfo1;
    @Mock
    private ISessionInfo sessionInfo2;
    @Mock
    private Provider<ISession> iSessionProvider;
    @Mock
    private ISession session;
    private List<ISessionInfo> sessionInfoList;
    private int lastIndex = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(sessionInfo1.getRater()).thenReturn("Rater1");
        when(sessionInfo2.getRater()).thenReturn("Rater2");
        when(iSessionProvider.get()).thenReturn(session);
        sessionInfoList = new ArrayList<>();

        currentSession = iSessionProvider.get();
        currentSession.setSessionInfo(sessionInfo2);
        when(currentSession.getSessionInfo()).thenReturn(sessionInfo2);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void startSessionCorrect() {
        sessionInfoList.add(sessionInfo1);
        sessionInfoList.add(sessionInfo2);
        String expectedSessionInfoInCurrentSession = sessionInfoList.remove(lastIndex).getRater();
        int expectedListSize = 1;

        assertEquals(expectedListSize, sessionInfoList.size());
        assertEquals(expectedSessionInfoInCurrentSession, currentSession.getSessionInfo().getRater());

    }


    @Test
    public void startSessionWrong() {
        currentSession = iSessionProvider.get();
        exceptionRule.expect(IndexOutOfBoundsException.class);
        currentSession.setSessionInfo(sessionInfoList.remove(0));

        int expectedListSize = 0;

        assertEquals(expectedListSize, sessionInfoList.size());

    }

    @Test
    public void endAllSessionsTrue() {
        sessionInfoList.add(sessionInfo1);
        sessionInfoList.add(sessionInfo2);
        currentSession = iSessionProvider.get();
        currentSession.setSessionInfo(sessionInfoList.remove(lastIndex));
        if (!sessionInfoList.isEmpty()) {
            this.currentSession.endSession();
            this.sessionInfoList.clear();
        }
        int expectedListSize = 0;

        assertEquals(expectedListSize, sessionInfoList.size());

    }

    @Test
    public void endAllSessionsFalse() {
        currentSession = iSessionProvider.get();
        exceptionRule.expect(IndexOutOfBoundsException.class);
        currentSession.setSessionInfo(sessionInfoList.remove(lastIndex));
        if (!sessionInfoList.isEmpty()) {
            this.currentSession.endSession();
            this.sessionInfoList.clear();
        }
        int expectedListSize = 0;

        assertEquals(expectedListSize, sessionInfoList.size());

    }
}