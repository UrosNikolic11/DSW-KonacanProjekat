package dsw.gerudok.app.gui.swing.controller;

import java.awt.*;

public class ActionManager {

    private ExitAction exitAction;
    private NewProjectAction newProjectAction;
    private AboutAction aboutAction;
    private DeleteAction deleteAction;
    private CopyAction copyAction;
    private PasteAction pasteAction;
    private PaintDeleteAction paintDeleteAction;
    private PaintResizeAction paintResizeAction;
    private PaintRotateAction paintRotateAction;
    private PaintRectangleAction paintRectangleAction;
    private PaintTriangleAction paintTriangleAction;
    private PaintCircleAction paintCircleAction;
    private PaintSelectAction paintSelectAction;
    private PaintMoveAction paintMoveAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private OpenAction openAction;
    private SaveAction saveAction;
    private SlotAction slotAction;
    private TextAction textAction;
    private SlikaAction slikaAction;

    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        aboutAction = new AboutAction();
        deleteAction = new DeleteAction();
        copyAction = new CopyAction();
        pasteAction = new PasteAction();
        paintCircleAction = new PaintCircleAction();
        paintDeleteAction = new PaintDeleteAction();
        paintMoveAction = new PaintMoveAction();
        paintSelectAction = new PaintSelectAction();
        paintTriangleAction = new PaintTriangleAction();
        paintRectangleAction = new PaintRectangleAction();
        paintResizeAction = new PaintResizeAction();
        paintRotateAction = new PaintRotateAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        openAction = new OpenAction();
        saveAction = new SaveAction();
        slotAction = new SlotAction();
        textAction = new TextAction();
        slikaAction = new SlikaAction();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public void setExitAction(ExitAction exitAction) {
        this.exitAction = exitAction;
    }

    public NewProjectAction getNewProjectAction() {
        return newProjectAction;
    }

    public void setNewProjectAction(NewProjectAction newProjectAction) {
        this.newProjectAction = newProjectAction;
    }

    public AboutAction getAboutAction() {
        return aboutAction;
    }

    public void setAboutAction(AboutAction aboutAction) {
        this.aboutAction = aboutAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(DeleteAction deleteAction) {
        this.deleteAction = deleteAction;
    }

    public CopyAction getCopyAction() {
        return copyAction;
    }

    public void setCopyAction(CopyAction copyAction) {
        this.copyAction = copyAction;
    }

    public PasteAction getPasteAction() {
        return pasteAction;
    }

    public void setPasteAction(PasteAction pasteAction) {
        this.pasteAction = pasteAction;
    }

    public PaintDeleteAction getPaintDeleteAction() {
        return paintDeleteAction;
    }

    public void setPaintDeleteAction(PaintDeleteAction paintDeleteAction) {
        this.paintDeleteAction = paintDeleteAction;
    }

    public PaintResizeAction getPaintResizeAction() {
        return paintResizeAction;
    }

    public void setPaintResizeAction(PaintResizeAction paintResizeAction) {
        this.paintResizeAction = paintResizeAction;
    }

    public PaintRotateAction getPaintRotateAction() {
        return paintRotateAction;
    }

    public void setPaintRotateAction(PaintRotateAction paintRotateAction) {
        this.paintRotateAction = paintRotateAction;
    }

    public PaintRectangleAction getPaintRectangleAction() {
        return paintRectangleAction;
    }

    public void setPaintRectangleAction(PaintRectangleAction paintRectangleAction) {
        this.paintRectangleAction = paintRectangleAction;
    }

    public PaintTriangleAction getPaintTriangleAction() {
        return paintTriangleAction;
    }

    public void setPaintTriangleAction(PaintTriangleAction paintTriangleAction) {
        this.paintTriangleAction = paintTriangleAction;
    }

    public PaintCircleAction getPaintCircleAction() {
        return paintCircleAction;
    }

    public void setPaintCircleAction(PaintCircleAction paintCircleAction) {
        this.paintCircleAction = paintCircleAction;
    }

    public PaintSelectAction getPaintSelectAction() {
        return paintSelectAction;
    }

    public void setPaintSelectAction(PaintSelectAction paintSelectAction) {
        this.paintSelectAction = paintSelectAction;
    }

    public PaintMoveAction getPaintMoveAction() {
        return paintMoveAction;
    }

    public void setPaintMoveAction(PaintMoveAction paintMoveAction) {
        this.paintMoveAction = paintMoveAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public void setUndoAction(UndoAction undoAction) {
        this.undoAction = undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public void setRedoAction(RedoAction redoAction) {
        this.redoAction = redoAction;
    }

    public OpenAction getOpenAction() {
        return openAction;
    }

    public void setOpenAction(OpenAction openAction) {
        this.openAction = openAction;
    }

    public SaveAction getSaveAction() {
        return saveAction;
    }

    public void setSaveAction(SaveAction saveAction) {
        this.saveAction = saveAction;
    }

    public SlotAction getSlotAction() {
        return slotAction;
    }

    public void setSlotAction(SlotAction slotAction) {
        this.slotAction = slotAction;
    }

    public TextAction getTextAction() {
        return textAction;
    }

    public void setTextAction(TextAction textAction) {
        this.textAction = textAction;
    }

    public SlikaAction getSlikaAction() {
        return slikaAction;
    }

    public void setSlikaAction(SlikaAction slikaAction) {
        this.slikaAction = slikaAction;
    }
}
