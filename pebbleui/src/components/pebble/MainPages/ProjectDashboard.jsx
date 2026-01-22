import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
//import { useParams } from "react-router-dom"; 
import dayjs from "dayjs";
import { retrieveProjectsApi } from "../../../services/ProjectService";
import { useAuth } from "../security/Authcontext";

export default function ProjectDashboard() {
  //const { projectId } = useParams();
  const navigate = useNavigate();
  const { userId, username } = useAuth();
  const [projects, setProjects] = useState([]);

  useEffect(() => {
    if (userId !== 0) {
      retrieveProjects(userId);
    }
  }, [userId]);

  async function retrieveProjects(userId) {
    console.log("Retrieving projects for user:", userId);
    try {
      const response = await retrieveProjectsApi(userId);
      console.log("Projects:", response.data);
      setProjects(response.data);
    } catch (error) {
      console.error("Error retrieving projects:", error);
    }
  }

  return (
    <>
      <h1>{username}'s List Of Projects</h1>
      <div className="card">
        <table className="table table-sm table-striped table-hover">
          <thead>
            <tr>
              <th>Name</th>
              <th>Description</th>
              <th>Target Date</th>
              <th>Topics</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {projects.map((project) => {
                
              const topics = [project.topic1, project.topic2, project.topic3];
              const targetDate = project.projectStartDate
                ? dayjs(project.projectStartDate).format("DD/MM/YYYY")
                : dayjs(project.creationDate).format("DD/MM/YYYY");

              return (
                <tr
                  key={project.id}
                  onClick={() => navigate(`/pebbles/${project.projectId}`, {state: {topics} })}
                  style={{ cursor: "pointer" }}
                >
                  <td>{project.projectName}</td>
                  <td>{project.projectDescription}</td>
                  <td>{project.projectStatus ? targetDate : "Not started"}</td>
                  <td>
                    {project.topic1 && <div>{project.topic1}</div>}
                    {project.topic2 && <div>{project.topic2}</div>}
                    {project.topic3 && <div>{project.topic3}</div>}
                  </td>
                  <td>
                    <button
                      className="btn btn-outline-secondary btn-sm mx-1"
                      onClick={(e) => {
                        e.stopPropagation();
                        navigate(`/project/${project.projectId}`);
                      }}
                    >
                      ✏️
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>

        <button
          className="btn btn-success rounded-circle position-absolute"
          style={{ bottom: "1rem", right: "1rem" }}
          onClick={() => navigate("/project/-1")}
        >
          +
        </button>
      </div>
    </>
  );
}
