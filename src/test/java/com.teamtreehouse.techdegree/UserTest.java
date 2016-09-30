package com.teamtreehouse.techdegree;

        import com.teamtreehouse.techdegree.exc.AnswerAcceptanceException;
        import com.teamtreehouse.techdegree.exc.VotingException;
        import org.junit.Before;
        import org.junit.Test;

        import static org.junit.Assert.*;

/**
 * Created by helloworld on 18/06/2016.
 * Updated 30/09
 */
public class UserTest {


    // Create additional objects which can be shared across tests.
    private Board bourd;

    private User questioner;
    private User voter;
    private  User answerer;

    private Question question;
    private Answer answer;


    @Before
    public void setUp() throws Exception {

        bourd = new Board("Topic");

        questioner = new User(bourd, "questioner");

        voter = new User(bourd, "voter");
        answerer = new User(bourd, "answerer");

        question = new Question(questioner, "some text");
        answer = new Answer(question,answerer,"answer text");

        bourd.addQuestion(question);
    }

    //Write a test to ensure that the questioner’s reputation goes up by 5 points if their question is upvoted.
    @Test
    public void gettingQuestionUpvotedImprovesReputation() throws Exception {

        int reputation = questioner.getReputation();

        bourd.getQuestions().get(0).addUpVoter(voter);

        assertEquals("question upvoted improves rep", reputation+5, questioner.getReputation());
    }

    //Write a test to assert that the answerer’s reputation goes up by 10 points if their answer is upvoted.
    @Test
    public void gettingAnswerUpvotedImprovesReputation() throws Exception {

        int preReputation = answerer.getReputation();

        answer.addUpVoter(voter);

        bourd.addAnswer(answer);

        assertEquals("answer upvoted improves rep",preReputation+10, answerer.getReputation());
    }

    //Write a test that that proves that having an answer accepted gives the answerer a 15 point reputation boost
    @Test
    public void gettingAnswerAcceptedImprovesReputation() throws Exception {

        int preReputation = answerer.getReputation();

        bourd.addAnswer(answer);

        answer.setAccepted(true);

        assertEquals("answer accepted improves rep", preReputation+15, answerer.getReputation());
    }


    //Using a test, ensure that voting either up or down is not allowed on questions or answers by the original author,
    // you know to avoid gaming the system. Ensure the proper exceptions are being thrown.

    // Exceptions thrown at the method level shoudl be "Exception" not a subclass.
    @Test(expected = VotingException.class)
    public void votingOwnPostsNotPermitted() throws Exception {


        questioner.upVote(question);

    }


    //Write a test to make sure that only the original questioner can accept an answer. Ensure the intended messaging
    // is being sent to back to the caller.
    @Test(expected = AnswerAcceptanceException.class)
    public void onlyQestionerCanAcceptAnswer() throws Exception {

        answerer.acceptAnswer(answer);

    }


    //Reviewing the User.getReputation method may expose some code that is not requested to be tested in the Meets Project
    // instructions. Write the missing test.
    @Test
    public void gettingPostDownvotedReducesReputation() throws Exception {

        int preReputation = answerer.getReputation();

        bourd.addAnswer(answer);

        answer.addDownVoter(voter);

        assertEquals("answer accepted improves rep", preReputation-1, answerer.getReputation());
    }

}