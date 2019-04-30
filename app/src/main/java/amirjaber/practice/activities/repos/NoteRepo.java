package amirjaber.practice.activities.repos;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import amirjaber.practice.activities.daos.NoteDao;
import amirjaber.practice.activities.dbconfig.NoteDB;
import amirjaber.practice.activities.models.Note;
import androidx.lifecycle.LiveData;

public class NoteRepo {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepo(Application application) {
        NoteDB db = NoteDB.getInstance(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNoteAsynchTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsynchTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsynchTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsynchTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsynchTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private InsertNoteAsynchTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsynchTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsynchTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsynchTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsynchTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsynchTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllNotesAsynchTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
