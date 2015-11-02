using UnityEngine;

namespace MyScript.States
{
    public class VRImageObjView : AbstractGazeInputState
    {
        private GameObject ImageUI;
        private GameObject Target;

        public VRImageObjView(StateManager managerRef, GameObject TargetObject) : base(managerRef, "VRImageObjectView")
        {
            Target = TargetObject;

            GameObject DisposolObj = GameObject.FindGameObjectWithTag("Disposol");
            if (DisposolObj) GameObject.Destroy(DisposolObj);

            ImageUI = GameObject.Find("ImageView");
            if (!ImageUI)
                Debug.Log("ImageSelector is Null");


            ImageUI.GetComponent<MeshCollider>().enabled = true;
            ImageUI.GetComponent<MeshRenderer>().enabled = true;
			SetGazeInputMode (GAZE_MODE.OFF);
			SetCameraLock (true);

        }
		protected override void HandleSelectOperation()
		{
			if (ImageUI == null)
				GameObject.Find ("ImageView");

			ImageUI.GetComponent<AbstractBasicObject>().OnSelect();
		}
        protected override void HandleCancelOperation()
        {
            base.HandleCancelOperation();
            ExitImageState();
        }

        void ExitImageState()
        {
            Debug.Log("Exit Image");
            SwitchState(new VRImageObjViewExit(Manager, Target));
        }

    }
}

