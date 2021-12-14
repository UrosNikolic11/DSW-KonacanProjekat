package dsw.gerudok.app.repository.state;

import dsw.gerudok.app.repository.Page;

public class StateManager {
        private State currentState;


        CircleState circleState;
        RectangleState rectangleState;
        SelectState selectState;
        TriangleState triangleState;
        MoveState moveState;
        ResizeState resizeState;
        RotateState rotateState;
        DeleteState deleteState;
        public StateManager(Page med)
        {

            circleState = new CircleState(med);
            rectangleState=new RectangleState(med);
            selectState=new SelectState(med);
            triangleState = new TriangleState(med);
            moveState = new MoveState(med);
            resizeState = new ResizeState(med);
            rotateState = new RotateState(med);
            deleteState = new DeleteState(med);
            //uklonio sam dodelu vrednosti za curent state
        }

        public void setCircleState() { currentState = circleState; }
        public void setRectangleState(){ currentState = rectangleState; }
        public void setSelectState(){ currentState = selectState; }
        public void setTriangleState(){currentState = triangleState;}
        public void setMoveState(){currentState = moveState;}
        public void setResizeState(){currentState = resizeState;}
        public void setRotateState(){currentState = rotateState;}
        public void setDeleteState(){currentState = deleteState;}
        public State getCurrentState() {
            return currentState;
        }
}
