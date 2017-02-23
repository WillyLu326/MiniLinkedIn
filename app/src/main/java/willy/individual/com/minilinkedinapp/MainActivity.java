package willy.individual.com.minilinkedinapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import willy.individual.com.minilinkedinapp.models.BasicInfo;
import willy.individual.com.minilinkedinapp.models.Education;
import willy.individual.com.minilinkedinapp.models.Experience;
import willy.individual.com.minilinkedinapp.models.Project;
import willy.individual.com.minilinkedinapp.utils.DateUtils;
import willy.individual.com.minilinkedinapp.utils.ModelUtils;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_EDUCATION  = 100;
    private static final int REQ_CODE_EXPERIENCE = 101;
    private static final int REQ_CODE_PROJECT    = 102;
    private static final int REQ_CODE_BASICINFO  = 103;

    private static final String SP_KEY_EDUCATION   = "sp_education";
    private static final String SP_KEY_EXPERIENCE  = "sp_experience";
    private static final String SP_KEY_PROJECT     = "sp_project";
    private static final String SP_KEY_BASICINFO   = "sp_basicinfo";

    private BasicInfo basicInfo;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<Project> projects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        setupUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_BASICINFO && resultCode == Activity.RESULT_OK) {
            basicInfo = data.getParcelableExtra(BasicInfoActivity.KEY_BASICINFO);
            updateBasicinfo(basicInfo);
        }

        if (requestCode == REQ_CODE_EDUCATION && resultCode == Activity.RESULT_OK) {
            String id = data.getStringExtra(EducationEditActivity.KEY_EDUCATION_DELETE);
            if (id != null) {
                deleteAndUpdateEducations(id);
            } else {
                Education education = data.getParcelableExtra(EducationEditActivity.KEY_EDUCATION);
                updateEducations(education);
            }
        }

        if (requestCode == REQ_CODE_EXPERIENCE && resultCode == Activity.RESULT_OK) {
            String id = data.getStringExtra(ExperienceEditActivity.KEY_EXPERIENCE_DELETE);
            if (id != null) {
                deleteAndUpdateExperience(id);
            } else {
                Experience experience = data.getParcelableExtra(ExperienceEditActivity.KEY_EXPERIENCE);
                updateExperiences(experience);
            }
        }

        if (requestCode == REQ_CODE_PROJECT && resultCode == Activity.RESULT_OK) {
            String id = data.getStringExtra(ProjectEditActivity.KEY_PROJECT_DELETE);
            if (id != null) {
                deleteAndUpdateProjects(id);
            } else {
                Project project = data.getParcelableExtra(ProjectEditActivity.KEY_PROJECT);
                updateProjects(project);
            }
        }
    }

    private void updateBasicinfo(BasicInfo basicInfo) {
        ModelUtils.saveModel(this, SP_KEY_BASICINFO, basicInfo);

        setupBasicInfo();
    }

    private void updateEducations(Education newEducation) {
        boolean found = false;
        for (int i = 0; i < educations.size(); ++i) {
            if (TextUtils.equals(newEducation.id, educations.get(i).id)) {
                educations.set(i, newEducation);
                found = true;
                break;
            }
        }

        if (!found) {
            educations.add(newEducation);
        }

        ModelUtils.saveModel(this, SP_KEY_EDUCATION, educations);

        setupEducations();
    }

    private void deleteAndUpdateEducations(String id) {
        for (int i = 0; i < educations.size(); ++i) {
            if (TextUtils.equals(educations.get(i).id, id)) {
                educations.remove(i);
                break;
            }
        }

        ModelUtils.saveModel(this, SP_KEY_EDUCATION, educations);

        setupEducations();
    }


    private void updateExperiences(Experience newExperience) {
        boolean found = false;
        for (int i = 0; i < experiences.size(); ++i) {
            if (TextUtils.equals(experiences.get(i).id, newExperience.id)) {
                experiences.set(i, newExperience);
                found = true;
                break;
            }
        }

        if (!found) {
            experiences.add(newExperience);
        }

        ModelUtils.saveModel(this, SP_KEY_EXPERIENCE, experiences);

        setupExperiences();
    }

    private void deleteAndUpdateExperience(String id) {
        for (int i = 0; i < experiences.size(); ++i) {
            if (TextUtils.equals(experiences.get(i).id, id)) {
                experiences.remove(i);
                break;
            }
        }

        ModelUtils.saveModel(this, SP_KEY_EXPERIENCE, experiences);

        setupExperiences();
    }


    private void updateProjects(Project newProject) {
        boolean found = false;
        for (int i = 0; i < projects.size(); ++i) {
            if (TextUtils.equals(newProject.id, projects.get(i).id)) {
                projects.set(i, newProject);
                found = true;
                break;
            }
        }

        if (!found) {
            projects.add(newProject);
        }

        ModelUtils.saveModel(this, SP_KEY_PROJECT, projects);

        setupProjects();
    }

    private void deleteAndUpdateProjects(String id) {
        for (int i = 0; i < projects.size(); ++i) {
            if (TextUtils.equals(projects.get(i).id, id)) {
                projects.remove(i);
                break;
            }
        }

        ModelUtils.saveModel(this, SP_KEY_PROJECT, projects);

        setupProjects();
    }

    private void setupUI() {
        setupBasicInfo();
        setupEducations();
        setupExperiences();
        setupProjects();
    }

    private void setupBasicInfo() {
        findViewById(R.id.profile_edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BasicInfoActivity.class);
                startActivityForResult(intent, REQ_CODE_BASICINFO);
            }
        });

        ((TextView) findViewById(R.id.profile_name)).setText(basicInfo.userName);
        ((TextView) findViewById(R.id.profile_email)).setText(basicInfo.email);
    }

    private void setupEducations() {
        // Education Add Button Listener
        findViewById(R.id.education_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDUCATION);
            }
        });

        LinearLayout educationsView = (LinearLayout) findViewById(R.id.educations_layout);
        educationsView.removeAllViews();
        for (Education education : educations) {
            View view = getEducationView(education);
            educationsView.addView(view);
        }
    }

    private void setupExperiences() {
        findViewById(R.id.experience_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExperienceEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EXPERIENCE);
            }
        });
        LinearLayout experiencesView = (LinearLayout) findViewById(R.id.experiences_layout);
        experiencesView.removeAllViews();
        for (Experience experience : experiences) {
            View view = getExperienceView(experience);
            experiencesView.addView(view);
        }
    }

    private void setupProjects() {
        findViewById(R.id.project_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProjectEditActivity.class);
                startActivityForResult(intent, REQ_CODE_PROJECT);
            }
        });
        LinearLayout projectsView = (LinearLayout) findViewById(R.id.projects_layout);
        projectsView.removeAllViews();
        for (Project project : projects) {
            View view  = getProjectView(project);
            projectsView.addView(view);
        }
    }

    private View getEducationView(final Education education) {
        View view = getLayoutInflater().inflate(R.layout.education_item, null);

        ((TextView) view.findViewById(R.id.education_info)).setText(education.schoolName + " " + education.major + " ("
                + DateUtils.dateToString(education.startDate) + " ~ "
                + DateUtils.dateToString(education.endDate) + ")");

        ((TextView) view.findViewById(R.id.education_courses)).setText(getEducationCourses(education));

        view.findViewById(R.id.education_edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                intent.putExtra(EducationEditActivity.KEY_EDUCATION, education);
                startActivityForResult(intent, REQ_CODE_EDUCATION);
            }
        });

        return view;
    }

    private View getExperienceView(final Experience experience) {
        View view = getLayoutInflater().inflate(R.layout.experience_item, null);

        ((TextView) view.findViewById(R.id.experience_item_info)).setText(experience.companyName + " " + experience.jobTitle + " ("
                + DateUtils.dateToString(experience.startDate) + " ~ "
                + DateUtils.dateToString(experience.endDate) + ")");

        ((TextView) view.findViewById(R.id.experience_item_summary)).setText(getExperienceSummary(experience));

        view.findViewById(R.id.experience_item_edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExperienceEditActivity.class);
                intent.putExtra(ExperienceEditActivity.KEY_EXPERIENCE, experience);
                startActivityForResult(intent, REQ_CODE_EXPERIENCE);
            }
        });

        return view;
    }

    private View getProjectView(final Project project) {
        View view = getLayoutInflater().inflate(R.layout.project_item, null);
        ((TextView) view.findViewById(R.id.project_item_info)).setText(project.projectName + " " + project.summary + " ("
                + DateUtils.dateToString(project.startDate) + " ~ "
                + DateUtils.dateToString(project.endDate) + ")");
        ((TextView) view.findViewById(R.id.project_item_content)).setText(getProjectTools(project));

        view.findViewById(R.id.project_item_edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProjectEditActivity.class);
                intent.putExtra(ProjectEditActivity.KEY_PROJECT, project);
                startActivityForResult(intent, REQ_CODE_PROJECT);
            }
        });

        return view;
    }

    private void loadData() {

        BasicInfo savedBasicInfo = ModelUtils.readModel(this, SP_KEY_BASICINFO, new TypeToken<BasicInfo>(){});
        basicInfo = savedBasicInfo == null ? new BasicInfo() : savedBasicInfo;

        List<Education> savedEducations = ModelUtils.readModel(this, SP_KEY_EDUCATION, new TypeToken<List<Education>>(){});
        educations = savedEducations == null ? new ArrayList<Education>() : savedEducations;

        List<Experience> savedExperiences = ModelUtils.readModel(this, SP_KEY_EXPERIENCE, new TypeToken<List<Experience>>(){});
        experiences = savedExperiences == null ? new ArrayList<Experience>() : savedExperiences;

        List<Project> savedProjects = ModelUtils.readModel(this, SP_KEY_PROJECT, new TypeToken<List<Project>>(){});
        projects = savedProjects == null ? new ArrayList<Project>() : savedProjects;
    }

    /**
     * Tools Functions
     * @param education
     * @return
     */
    private String getEducationCourses(Education education) {
        String result = "";
        for (int i = 0; i < education.courses.size(); ++i) {
            if (i == education.courses.size() - 1) {
                result += "- " + education.courses.get(i);
            } else {
                result += "- " + education.courses.get(i) + "\n";
            }
        }
        return result;
    }

    private String getExperienceSummary(Experience experience) {
        String result = "";
        for (int i = 0; i < experience.projects.size(); ++i) {
            if (i == experience.projects.size() - 1) {
                result += "- " + experience.projects.get(i);
            } else {
                result += "- " + experience.projects.get(i) + "\n";
            }
        }
        return result;
    }

    private String getProjectTools(Project project) {
        String result = "";
        for (int i = 0; i < project.tools.size(); ++i) {
            if (i == project.tools.size() - 1) {
                result += "- " + project.tools.get(i);
            } else {
                result += "- " + project.tools.get(i) + "\n";
            }
        }
        return result;
    }

}
